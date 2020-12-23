package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.callback.*;
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
    public static final String FS_DATE_PATTERN = "dd.MM.yyy";
    public static final String CHARGE_COMPLETED = "charge-completed";
    public static final String CHARGE_FAILED = "charge-failed";
    public static final String DEACTIVATED = "deactivated";
    public static final String OK = "OK";

    PersonRepository personRepository;
    SubscriptionRepository subscriptionRepository;

    public FastSpringServiceImpl(PersonRepository personRepository,
                                 SubscriptionRepository subscriptionRepository) {
        this.personRepository = personRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    Consumer<SubscriptionCallbackCommon> createNewcomer = this::createPerson;
    BiConsumer<Person, SubscriptionCallbackCommon> updateSubscriptionInfo = this::updatePersonSubscription;

    @Override
    public String subscriptionActivated(SubscriptionCallbackCommon callback) {
        String idUser = getUserIdFromCallback(callback.getAccount());
        Optional<Person> oPerson = personRepository.findByFastSpringId(idUser);
        log.info("Subscription activated, user id is: {}, subscription id is: {}",
                idUser, callback.getSubscription());
        if (oPerson.isPresent()) {
            updateSubscriptionInfo.accept(oPerson.get(), callback);
        } else {
            createNewcomer.accept(callback);
        }
        return OK;
    }

    @Override
    public String subscriptionDeactivated(SubscriptionCallbackCommon callback) {
        log.info("Request for subscription Deactivated {}", callback.getAccount().getId());
        return updatePersonInfo(callback, DEACTIVATED);
    }

    @Override
    public String chargeFailed(SubscriptionCallbackCommon callback) {
        log.info("Request for subscription ChargeFailed {}", callback.getAccount().getId());
        return updatePersonInfo(callback, CHARGE_FAILED);
    }

    @Override
    public String chargeCompleted(SubscriptionCallbackCommon callback) {
        log.info("Request for subscription chargeCompleted {}", callback);
        return updatePersonInfo(callback, CHARGE_COMPLETED);
    }

    private String updatePersonInfo(SubscriptionCallbackCommon callback, String type) {
        String idUser = getUserIdFromCallback(callback.getAccount());
        Optional<Person> oPerson = personRepository.findByFastSpringId(idUser);
        log.info("Subscription" + type + ", user id is: {}, subscription id is: {}",
                idUser, callback.getSubscription());
        if (!oPerson.isPresent()) {
            throw new SubscriptionException("No user with this id present : " + idUser);
        }
        updateSubscriptionInfo.accept(oPerson.get(), callback);
        return OK;
    }

    private String getUserIdFromCallback(Account account) {
        return Optional.ofNullable(account)
                .map(Account::getId)
                .orElseThrow(() -> new SubscriptionException("No user id present" + LocalDateTime.now()));
    }

    private String getProduct(SubscriptionCallbackCommon callback) {
        return Optional.ofNullable(callback.getProduct())
                .map(Product::getProduct)
                .orElseThrow(() -> new SubscriptionException("no product name present"));
    }

    private Contact getContact(SubscriptionCallbackCommon callback) {
        return Optional.ofNullable(callback.getAccount())
                .map(Account::getContact)
                .orElseThrow(() -> new SubscriptionException("Contact is empty"));
    }

    private void createPerson(SubscriptionCallbackCommon callback) {
        Person newcomer = new Person();
        newcomer.setFastSpringId(callback.getAccount().getId());
        Contact contact = getContact(callback);
        newcomer.setEmail(contact.getEmail());
        newcomer.setName(contact.getFirst() + " " + contact.getLast());
        personRepository.save(newcomer);
        createSubscription(callback, newcomer);
    }

    private void createSubscription(SubscriptionCallbackCommon callback, Person newcomer) {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(callback.getSubscription());
        subscription.setPerson(newcomer);
        subscription.setSubscriptionId(callback.getSubscription());
        subscription.setActive(callback.active);
        subscription.setState(callback.getState());
        subscription.setFrom(LocalDate.now());
        subscription.setTo(Optional.ofNullable(formatDateFastSpring(callback.getNextChargeDateDisplay()))
                .orElse(LocalDate.now()));
        subscription.setType(getProduct(callback));
        subscriptionRepository.save(subscription);
    }

    private void updatePersonSubscription(Person person, SubscriptionCallbackCommon callback) {
        person.getSubscriptions()
                .stream()
                .filter(subs -> subs.getType().equalsIgnoreCase(getProduct(callback)))
                .findFirst()
                .map(subs -> updateSubscription(callback, subs))
                .map(subs -> subscriptionRepository.save(subs))
                .orElseThrow(() -> new SubscriptionException("Cannot get subscription info"));
    }

    private Subscription updateSubscription(SubscriptionCallbackCommon callback, Subscription subs) {
        subs.setFrom(LocalDate.now());
        subs.setTo(Optional.ofNullable(formatDateFastSpring(callback.getNextChargeDateDisplay()))
                .orElse(LocalDate.now()));
        subs.setActive(callback.active);
        subs.setState(callback.getState());
        subs.setSubscriptionId(callback.getSubscription());
        return subs;
    }

    private LocalDate formatDateFastSpring(String fastSpringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FS_DATE_PATTERN);
        if (fastSpringDate != null) {
            return LocalDate.parse(fastSpringDate, formatter);
        }
        return null;
    }
}
