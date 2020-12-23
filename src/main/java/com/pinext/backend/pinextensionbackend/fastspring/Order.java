package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {
    public String order;
    public String id;
    public String reference;
    public boolean completed;
    public long changed;
    public long changedValue;
    public int changedInSeconds;
    public String changedDisplay;
    public String language;
    public boolean live;
    public String currency;
    public String payoutCurrency;
    public String invoiceUrl;
    public String account;
    public int total;
    public String totalDisplay;
    public int totalInPayoutCurrency;
    public String totalInPayoutCurrencyDisplay;
    public int tax;
    public String taxDisplay;
    public int taxInPayoutCurrency;
    public String taxInPayoutCurrencyDisplay;
    public int subtotal;
    public String subtotalDisplay;
    public int subtotalInPayoutCurrency;
    public String subtotalInPayoutCurrencyDisplay;
    public int discount;
    public String discountDisplay;
    public int discountInPayoutCurrency;
    public String discountInPayoutCurrencyDisplay;
    public int discountWithTax;
    public String discountWithTaxDisplay;
    public int discountWithTaxInPayoutCurrency;
    public String discountWithTaxInPayoutCurrencyDisplay;
    public String billDescriptor;
    public Payment payment;
    public Customer customer;
    public Address address;
    public List<Recipient> recipients;
    public List<Item> items;
}
