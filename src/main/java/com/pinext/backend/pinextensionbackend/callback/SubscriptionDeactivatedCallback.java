package com.pinext.backend.pinextensionbackend.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pinext.backend.pinextensionbackend.fastspring.*;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionDeactivatedCallback {
    public String id;
    public String subscription;
    public boolean active;
    public String state;
    public long changed;
    public long changedValue;
    public int changedInSeconds;
    public String changedDisplay;
    public boolean live;
    public String currency;
    public Account account;
    public Product product;
    public String sku;
    public String display;
    public int quantity;
    public boolean adhoc;
    public boolean autoRenew;
    public int price;
    public String priceDisplay;
    public int priceInPayoutCurrency;
    public String priceInPayoutCurrencyDisplay;
    public int discount;
    public String discountDisplay;
    public int discountInPayoutCurrency;
    public String discountInPayoutCurrencyDisplay;
    public int subtotal;
    public String subtotalDisplay;
    public int subtotalInPayoutCurrency;
    public String subtotalInPayoutCurrencyDisplay;
    public long next;
    public long nextValue;
    public int nextInSeconds;
    public String nextDisplay;
    public long end;
    public long endValue;
    public int endInSeconds;
    public String endDisplay;
    public long canceledDate;
    public long canceledDateValue;
    public int canceledDateInSeconds;
    public String canceledDateDisplay;
    public int sequence;
    public int periods;
    public int remainingPeriods;
    public long begin;
    public long beginValue;
    public int beginInSeconds;
    public String beginDisplay;
    public String intervalUnit;
    public int intervalLength;
    public TrialReminder trialReminder;
    public PaymentReminder paymentReminder;
    public PaymentOverdue paymentOverdue;
    public CancellationSetting cancellationSetting;
    public Fulfillments2 fulfillments;
    public List<Instruction> instructions;
}
