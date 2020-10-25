package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.exception.SubscriptionException;
import com.pinext.backend.pinextensionbackend.repository.PersonRepository;
import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.SubscriptionCallbackRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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
    public Boolean processCallback(SubscriptionCallbackRequest request) {
        return null;
    }
}
