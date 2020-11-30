package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.callback.SubscriptionActivatedCallback;

public interface FastSpringService {
    String subscriptionActivated(SubscriptionActivatedCallback callback);
}
