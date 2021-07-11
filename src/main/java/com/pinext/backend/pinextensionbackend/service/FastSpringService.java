package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.callback.SubscriptionCallbackCommon;

public interface FastSpringService {
    String subscriptionActivated(SubscriptionCallbackCommon callback);

    String subscriptionDeactivated(SubscriptionCallbackCommon callback);

    String chargeFailed(SubscriptionCallbackCommon callback);

    String chargeCompleted(SubscriptionCallbackCommon callback);
}
