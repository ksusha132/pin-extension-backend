package com.pinext.backend.pinextensionbackend.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(description = "Check user subscription request")
public class CheckUserSubscriptionRequest {
    @ApiModelProperty("user name")
    private String name;
    @ApiModelProperty("login")
    private String login;
    @NotNull
    @ApiModelProperty("email")
    private String email;
    @NotNull
    @ApiModelProperty("subscription type")
    private String subscriptionType;
}
