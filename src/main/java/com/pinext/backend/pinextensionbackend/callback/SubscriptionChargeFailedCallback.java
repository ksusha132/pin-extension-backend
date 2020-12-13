package com.pinext.backend.pinextensionbackend.callback;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionChargeFailedCallback extends SubscriptionCallbackCommon {
    public String reason;
}
