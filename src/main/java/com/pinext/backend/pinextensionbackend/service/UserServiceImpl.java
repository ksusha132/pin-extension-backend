package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.exception.SubscriptionException;
import com.pinext.backend.pinextensionbackend.repository.PersonRepository;
import com.pinext.backend.pinextensionbackend.repository.SubscriptionRepository;
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

    public static final String ACTIVE = "active";

    private final PersonRepository personRepository;

    public UserServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public CheckSubscriptionResponse checkSubscription(CheckUserSubscriptionRequest request) {
        log.info("Subscription request came for: {}, at: {}, subscription type is: {}",
                request.getEmail(), LocalDateTime.now(), request.getSubscriptionType());

        return Optional.ofNullable(personRepository
                .findByEmailAndTypeAndState(request.getEmail(), request.getSubscriptionType(), ACTIVE))
                .map(action -> buildResponse(true))
                .orElse(buildResponse(false));
    }

    private CheckSubscriptionResponse buildResponse(boolean b) {
        return CheckSubscriptionResponse.builder()
                .status(b)
                .time(LocalDateTime.now())
                .build();
    }
}
