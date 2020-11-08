package com.pinext.backend.pinextensionbackend.callback;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pinext.backend.pinextensionbackend.fastspring.*;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubscriptionUncanceledCallback {
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
    public int sequence;
    public long begin;
    public long beginValue;
    public int beginInSeconds;
    public String beginDisplay;
    public String intervalUnit;
    public int intervalLength;
    public String nextChargeCurrency;
    public long nextChargeDate;
    public long nextChargeDateValue;
    public int nextChargeDateInSeconds;
    public String nextChargeDateDisplay;
    public int nextChargePreTax;
    public String nextChargePreTaxDisplay;
    public int nextChargePreTaxInPayoutCurrency;
    public String nextChargePreTaxInPayoutCurrencyDisplay;
    public int nextChargeTotal;
    public String nextChargeTotalDisplay;
    public int nextChargeTotalInPayoutCurrency;
    public String nextChargeTotalInPayoutCurrencyDisplay;
    public String nextNotificationType;
    public long nextNotificationDate;
    public long nextNotificationDateValue;
    public int nextNotificationDateInSeconds;
    public String nextNotificationDateDisplay;
    public TrialReminder trialReminder;
    public PaymentReminder paymentReminder;
    public PaymentOverdue paymentOverdue;
    public CancellationSetting cancellationSetting;
    public Fulfillments2 fulfillments;
    public List<Instruction> instructions;
}
