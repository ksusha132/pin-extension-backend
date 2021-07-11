package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExampleSubscriptionMonthlyLicense0 {
    public String fulfillment;
    public String name;
    public String applicability;
}
