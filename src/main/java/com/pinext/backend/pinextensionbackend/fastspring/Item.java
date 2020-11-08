package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class Item {
    public String product;
    public int quantity;
    public String display;
    public String sku;
    public int subtotal;
    public String subtotalDisplay;
    public int subtotalInPayoutCurrency;
    public String subtotalInPayoutCurrencyDisplay;
    public int discount;
    public String discountDisplay;
    public int discountInPayoutCurrency;
    public String discountInPayoutCurrencyDisplay;
    public String subscription;
    public Fulfillments fulfillments;
}
