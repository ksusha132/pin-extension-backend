package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.callback.SubscriptionActivatedCallback;
import com.pinext.backend.pinextensionbackend.callback.SubscriptionChargeCompletedCallback;
import com.pinext.backend.pinextensionbackend.callback.SubscriptionChargeFailedCallback;
import com.pinext.backend.pinextensionbackend.callback.SubscriptionDeactivatedCallback;

public interface FastSpringService {
    String subscriptionActivated(SubscriptionActivatedCallback callback);

    String subscriptionDeactivated(SubscriptionDeactivatedCallback callback);

    String chargeFailed(SubscriptionChargeFailedCallback callback);

    String chargeCompleted(SubscriptionChargeCompletedCallback callback);
}
