package com.pinext.backend.pinextensionbackend.controller;

import com.pinext.backend.pinextensionbackend.request.CheckUserSubscriptionRequest;
import com.pinext.backend.pinextensionbackend.request.CreateAccountRequest;
import com.pinext.backend.pinextensionbackend.response.CheckSubscriptionResponse;
import com.pinext.backend.pinextensionbackend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static com.pinext.backend.pinextensionbackend.constans.Constants.CHECK_SUBSCRIPTION;
import static com.pinext.backend.pinextensionbackend.constans.Constants.GET_UUID;

@RestController
@Api("Check subscription")
public class UserController {

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

    @ApiOperation("Get uuid user.")
    @PostMapping(GET_UUID)
    public ResponseEntity<UUID> getUserUuid(
            @ApiParam(value = "Get uuid user by email")
            @RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(userService.createUUidIfNotPresent(request));
    }
}
