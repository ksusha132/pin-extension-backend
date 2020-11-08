package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class Product {
    public String product;
    public Display display;
    public Description description;
    public String image;
    public String sku;
    public Fulfillments fulfillments;
    public String format;
    public Pricing pricing;
}
