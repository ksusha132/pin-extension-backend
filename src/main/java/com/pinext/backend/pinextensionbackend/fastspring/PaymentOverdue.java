package com.pinext.backend.pinextensionbackend.fastspring;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentOverdue {
    public String intervalUnit;
    public int intervalLength;
    public int total;
    public int sent;
}
