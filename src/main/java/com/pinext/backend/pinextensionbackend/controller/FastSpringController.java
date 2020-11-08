package com.pinext.backend.pinextensionbackend.controller;

import com.pinext.backend.pinextensionbackend.request.SubscriptionCallbackRequest;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("API FastSpring operation")
public class FastSpringController {
    public static final String BASE_PATH = "/api/v1";
    public static final String CALLBACK_SUBS_ACTIVATED = BASE_PATH + "/subscription-activated";

    @PostMapping(CALLBACK_SUBS_ACTIVATED)
    public ResponseEntity<?> fastSprionCallback(@RequestBody SubscriptionCallbackRequest request) {
        return ResponseEntity.ok(null);
    }
}
