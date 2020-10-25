package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.entity.Person;
import com.pinext.backend.pinextensionbackend.entity.Subscription;
import com.pinext.backend.pinextensionbackend.exception.SubscriptionException;
import com.pinext.backend.pinextensionbackend.exception.UserNotFoundException;
import com.pinext.backend.pinextensionbackend.repository.PersonRepository;
import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.SubscriptionCallbackRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

        Boolean subscription = personRepository.findByEmail(request.getEmail())
                .map(person -> getSubscriptionStatus(request, person))
                .orElseThrow(() -> new UserNotFoundException("Cannot find user by provided email: "
                        + request.getEmail()));

        log.info("Subscription response with status: {}, time: {}, for email: {}",
                subscription, LocalDateTime.now(), request.getEmail());

        return CheckSubscriptionResponse.builder()
                .status(subscription)
                .time(LocalDateTime.now())
                .build();
    }

    @Override
    public Boolean processCallback(SubscriptionCallbackRequest request) {
        return null;
    }

    private Boolean getSubscriptionStatus(CheckUserSubscriptionRequest request, Person person) {
        return person.getSubscriptions()
                .stream()
                .filter(subs -> subs.getType().equalsIgnoreCase(request.getSubscriptionType()))
                .map(Subscription::getActive)
                .findFirst()
                .orElseThrow(() -> new SubscriptionException("Cannot get subscription status"
                        + request.getEmail()));
    }
}
