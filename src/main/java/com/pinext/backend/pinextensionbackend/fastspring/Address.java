package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    public String city;
    public String regionCode;
    public String regionDisplay;
    public String region;
    public String postalCode;
    public String country;
    public String display;
}
