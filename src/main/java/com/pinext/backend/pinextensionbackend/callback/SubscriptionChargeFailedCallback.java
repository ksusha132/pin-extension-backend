package com.pinext.backend.pinextensionbackend.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pinext.backend.pinextensionbackend.fastspring.Account;
import com.pinext.backend.pinextensionbackend.fastspring.Subscription;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionChargeFailedCallback {
    public String reason;
    public Account account;
    public Subscription subscription;
}
