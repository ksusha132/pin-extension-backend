package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.CreateAccountRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;

import java.util.UUID;

public interface UserService {
    CheckSubscriptionResponse checkSubscription(CheckUserSubscriptionRequest request);

    UUID createUUidIfNotPresent(CreateAccountRequest request);
}
