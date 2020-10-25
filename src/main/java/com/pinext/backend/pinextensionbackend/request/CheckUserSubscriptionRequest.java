package com.pinext.backend.pinextensionbackend.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CheckUserSubscriptionRequest {
    private String name;
    private String login;
    @NotNull
    private String email;
    @NotNull
    private String subscriptionType;
}
