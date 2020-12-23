package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExampleSubscriptionMonthlyLicense12 {
    public String display;
    public int size;
    public String file;
    public String type;
}
