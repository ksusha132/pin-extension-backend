package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.SubscriptionCallbackRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;

public interface UserService {
    CheckSubscriptionResponse checkSubscription(CheckUserSubscriptionRequest request);

    Boolean processCallback(SubscriptionCallbackRequest request);
}
