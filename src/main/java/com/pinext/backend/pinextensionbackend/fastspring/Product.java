package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
