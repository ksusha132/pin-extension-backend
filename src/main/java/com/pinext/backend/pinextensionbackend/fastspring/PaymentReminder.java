package com.pinext.backend.pinextensionbackend.fastspring;

import lombok.Data;

@Data
public class PaymentReminder {
    public String intervalUnit;
    public int intervalLength;
}
