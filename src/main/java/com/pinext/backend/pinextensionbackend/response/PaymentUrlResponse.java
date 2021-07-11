package com.pinext.backend.pinextensionbackend.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentUrlResponse {
    private String url;
    private LocalDateTime dateTime;
    private String email;
}
