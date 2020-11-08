package com.pinext.backend.pinextensionbackend.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pinext.backend.pinextensionbackend.fastspring.Account;
import com.pinext.backend.pinextensionbackend.fastspring.Order;
import com.pinext.backend.pinextensionbackend.fastspring.Subscription;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionChargeCompletedCallback {
    public Order order;
    public String currency;
    public int total;
    public String status;
    public long timestamp;
    public long timestampValue;
    public int timestampInSeconds;
    public String timestampDisplay;
    public int sequence;
    public Account account;
    public Subscription subscription;
}
