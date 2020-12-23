package com.pinext.backend.pinextensionbackend.callback;

import lombok.Data;

@Data
public class SubscriptionChargeFailedCallback extends SubscriptionCallbackCommon {
    public String reason;
}
