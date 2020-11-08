package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Fulfillments2 {
    @JsonProperty("example-subscription-monthly_license_0")
    public List<ExampleSubscriptionMonthlyLicense02> exampleSubscriptionMonthlyLicense0;
    @JsonProperty("example-subscription-monthly_file_0")
    public List<ExampleSubscriptionMonthlyFile02> exampleSubscriptionMonthlyFile0;
    @JsonProperty("example-subscription-monthly_license_1")
    public List<ExampleSubscriptionMonthlyLicense12> exampleSubscriptionMonthlyLicense1;
    public String instructions;
}
