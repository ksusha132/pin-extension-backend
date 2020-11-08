package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class PaymentOverdue {
    public String intervalUnit;
    public int intervalLength;
    public int total;
    public int sent;
}
