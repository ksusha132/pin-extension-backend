package com.pinext.backend.pinextensionbackend.service.impl;

import com.pinext.backend.pinextensionbackend.entity.Person;
import com.pinext.backend.pinextensionbackend.exception.SubscriptionException;
import com.pinext.backend.pinextensionbackend.repository.PersonRepository;
import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.CreateAccountRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;
import com.pinext.backend.pinextensionbackend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final PersonRepository personRepository;

    public UserServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    public CheckSubscriptionResponse checkSubscription(CheckUserSubscriptionRequest request) {
        log.info("Subscription request came for: {}, at: {}, subscription type is: {}",
                request.getEmail(), LocalDateTime.now(), request.getSubscriptionType());

        Boolean subscription = Optional.ofNullable(personRepository.findByEmailAndType(request.getEmail(),
                request.getSubscriptionType()))
                .orElseThrow(() -> new SubscriptionException("Cannot get status of the subscription."));

        log.info("Subscription response with status: {}, time: {}, for email: {}",
                subscription, LocalDateTime.now(), request.getEmail());

        return CheckSubscriptionResponse.builder()
                .status(subscription)
                .time(LocalDateTime.now())
                .build();
    }

    @Override
    public UUID createUUidIfNotPresent(CreateAccountRequest request) {
        //check
        Person person = personRepository.findByEmail(request.getEmail());
        if (Objects.nonNull(person)) {
            log.info("Person found: {}", person);
            return person.getId();
        }
        Person newOne = new Person();
        newOne.setEmail(request.getEmail());
        Person newSaved = personRepository.save(newOne);
        return newSaved.getId();
    }
}
