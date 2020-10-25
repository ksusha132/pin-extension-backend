package com.pinext.backend.pinextensionbackend.controller;

import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.SubscriptionCallbackRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;
import com.pinext.backend.pinextensionbackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    public static final String BASE_PATH = "/api/v1";
    public static final String CHECK_SUBSCRIPTION = BASE_PATH + "/check-subscription";
    public static final String CALLBACK = BASE_PATH + "/subscription-callback";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(CHECK_SUBSCRIPTION)
    public ResponseEntity<CheckSubscriptionResponse> checkIfUserHasSubscription(@RequestBody CheckUserSubscriptionRequest request) {
        return ResponseEntity.ok(userService.checkSubscription(request));
    }

    @PostMapping(CALLBACK)
    public ResponseEntity<?> checkIfUserHasSubscription(@RequestBody SubscriptionCallbackRequest request) {
        return ResponseEntity.ok(userService.processCallback(request));
    }
}
