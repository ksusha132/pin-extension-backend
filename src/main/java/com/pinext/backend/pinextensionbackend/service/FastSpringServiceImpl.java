package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.callback.SubscriptionActivatedCallback;
import com.pinext.backend.pinextensionbackend.entity.Person;
import com.pinext.backend.pinextensionbackend.entity.Subscription;
import com.pinext.backend.pinextensionbackend.exception.SubscriptionException;
import com.pinext.backend.pinextensionbackend.fastspring.Account;
import com.pinext.backend.pinextensionbackend.fastspring.Contact;
import com.pinext.backend.pinextensionbackend.fastspring.Product;
import com.pinext.backend.pinextensionbackend.repository.PersonRepository;
import com.pinext.backend.pinextensionbackend.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Slf4j
@Service
public class FastSpringServiceImpl implements FastSpringService {

    public static final String FS_DATE_PATTERN = "d/M/yy";

    PersonRepository personRepository;
    SubscriptionRepository subscriptionRepository;

    public FastSpringServiceImpl(PersonRepository personRepository,
                                 SubscriptionRepository subscriptionRepository) {
        this.personRepository = personRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    Consumer<SubscriptionActivatedCallback> createNewcomer = this::createPerson;
    BiConsumer<Person, SubscriptionActivatedCallback> updateSubscriptionInfo = this::updatePersonSubscription;

    @Override
    public String subscriptionActivated(SubscriptionActivatedCallback callback) {
        String userId = Optional.ofNullable(callback.getAccount())
                .map(Account::getId)
                .orElseThrow(() -> new SubscriptionException("No user id present" + LocalDateTime.now()));

        personRepository.findById(userId)
                .ifPresentOrElse(person -> updateSubscriptionInfo.accept(person, callback),
                        () -> createNewcomer.accept(callback));
        return "OK";
    }

    private String getProduct(SubscriptionActivatedCallback callback) {
        return Optional.ofNullable(callback.getProduct())
                .map(Product::getProduct)
                .orElseThrow(() -> new SubscriptionException("no product name present"));
    }

    private Contact getContact(SubscriptionActivatedCallback callback) {
        return Optional.ofNullable(callback.getAccount())
                .map(Account::getContact)
                .orElseThrow(() -> new SubscriptionException("Contact is empty"));
    }

    private void createPerson(SubscriptionActivatedCallback callback) {
        Person newcomer = new Person();
        newcomer.setId(callback.getId());
        Contact contact = getContact(callback);
        newcomer.setEmail(contact.getEmail());
        newcomer.setName(contact.getFirst() + " " + contact.getLast());
        createSubscription(callback, newcomer);
    }

    private void createSubscription(SubscriptionActivatedCallback callback, Person newcomer) {
        Subscription subscription = new Subscription();
        subscription.setPerson(newcomer);
        subscription.setActive(callback.active);
        subscription.setFrom(LocalDate.now());
        subscription.setTo(formatDateFastSpring(callback.getNextChargeDateDisplay()));
        subscription.setType(getProduct(callback));
    }

    private void updatePersonSubscription(Person person, SubscriptionActivatedCallback callback) {
        person.getSubscriptions()
                .stream()
                .filter(subs -> subs.getType().equalsIgnoreCase(getProduct(callback)))
                .findFirst()
                .map(subs -> updateSubscription(callback, subs))
                .map(subs -> subscriptionRepository.save(subs))
                .orElseThrow(() -> new SubscriptionException("Cannot get subscription info"));
    }

    private Subscription updateSubscription(SubscriptionActivatedCallback callback, Subscription subs) {
        subs.setFrom(LocalDate.now());
        subs.setTo(formatDateFastSpring(callback.getNextChargeDateDisplay()));
        subs.setActive(callback.active);
        return subs;
    }

    private LocalDate formatDateFastSpring(String fastSpringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FS_DATE_PATTERN);
        if (fastSpringDate != null) {
            return LocalDate.parse(fastSpringDate, formatter);
        }
        throw new SubscriptionException("No date subscription TO date present");
    }
}
