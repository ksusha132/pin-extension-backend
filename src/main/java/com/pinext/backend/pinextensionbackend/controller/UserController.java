package com.pinext.backend.pinextensionbackend.controller;

import com.pinext.backend.pinextensionbackend.request.*;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;
import com.pinext.backend.pinextensionbackend.response.CreateAccountResponse;
import com.pinext.backend.pinextensionbackend.response.OrderResponse;
import com.pinext.backend.pinextensionbackend.response.SubscriptionResponse;
import com.pinext.backend.pinextensionbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("API FastSpring operation")
public class UserController {

    public static final String BASE_PATH = "/api/v1";
    public static final String CHECK_SUBSCRIPTION = BASE_PATH + "/check-subscription";
    public static final String UNSUBSCRIBE = BASE_PATH + "/unsubscribe";
    public static final String SUBSCRIBE = BASE_PATH + "/subscribe";
    public static final String CALLBACK = BASE_PATH + "/subscription-callback";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("Check user subscription")
    @PostMapping(CHECK_SUBSCRIPTION)
    public ResponseEntity<CheckSubscriptionResponse> checkIfUserHasSubscription(
            @ApiParam(value = "Check user subscription request.")
            @RequestBody CheckUserSubscriptionRequest request) {
        return ResponseEntity.ok(userService.checkSubscription(request));
    }

    @ApiOperation("Cancel user subscription. FastSpring - subscription.canceled")
    @PostMapping(UNSUBSCRIBE)
    public ResponseEntity<SubscriptionResponse> unsubscribe(@RequestBody SubscriptionRequest request) {
        return null;
    }

    @ApiOperation("Activate user subscription. FastSpring - subscription.activated")
    @PostMapping(SUBSCRIBE)
    public ResponseEntity<SubscriptionResponse> subscribe(@RequestBody SubscriptionRequest request) {
        return null;
    }

    @PostMapping(CALLBACK)
    public ResponseEntity<?> fastSprionCallback(@RequestBody SubscriptionCallbackRequest request) {
        return ResponseEntity.ok(userService.processCallback(request));
    }
}
