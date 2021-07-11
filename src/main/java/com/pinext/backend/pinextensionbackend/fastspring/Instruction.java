package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Instruction {
    public String type;
    public int periodStartDateInSeconds;
    public String periodStartDateDisplay;
    public long periodEndDate;
    public long periodEndDateValue;
    public int periodEndDateInSeconds;
    public String periodEndDateDisplay;
    public String discountDurationUnit;
    public int discountDurationLength;
    public int discountPercent;
    public int discountPercentValue;
    public String discountPercentDisplay;
    public int unitDiscount;
    public String unitDiscountDisplay;
    public int unitDiscountInPayoutCurrency;
    public String unitDiscountInPayoutCurrencyDisplay;
    public int discountTotal;
    public String discountTotalDisplay;
    public int discountTotalInPayoutCurrency;
    public String discountTotalInPayoutCurrencyDisplay;
    public int total;
    public String totalDisplay;
    public int totalInPayoutCurrency;
    public String totalInPayoutCurrencyDisplay;
    public int price;
    public String priceDisplay;
    public int priceInPayoutCurrency;
    public String priceInPayoutCurrencyDisplay;
    public int priceTotal;
    public String priceTotalDisplay;
    public int priceTotalInPayoutCurrency;
    public String priceTotalInPayoutCurrencyDisplay;
    public int unitPrice;
    public String unitPriceDisplay;
    public int unitPriceInPayoutCurrency;
    public String unitPriceInPayoutCurrencyDisplay;
    public String product;
    public String intervalUnit;
    public int intervalLength;
}
