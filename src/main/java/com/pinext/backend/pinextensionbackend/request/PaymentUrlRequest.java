package com.pinext.backend.pinextensionbackend.request;


import lombok.Data;


@Data
//todo jsr309 not null
public class PaymentUrlRequest {
    private String email;
    private Double amount;
    private String productName;
    private String versionDescription;
    private String widgetCode;
}
