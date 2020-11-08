package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Fulfillments {
    public Instructions instructions;
    @JsonProperty("example-subscription-monthly_license_0")
    public ExampleSubscriptionMonthlyLicense0 exampleSubscriptionMonthlyLicense0;
    @JsonProperty("example-subscription-monthly_file_0")
    public ExampleSubscriptionMonthlyFile0 exampleSubscriptionMonthlyFile0;
    @JsonProperty("example-subscription-monthly_email_0")
    public ExampleSubscriptionMonthlyEmail0 exampleSubscriptionMonthlyEmail0;
    @JsonProperty("example-subscription-monthly_license_1")
    public ExampleSubscriptionMonthlyLicense1 exampleSubscriptionMonthlyLicense1;
}
