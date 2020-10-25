package com.pinext.backend.pinextensionbackend.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("Health status controller")
public class HealthController {

    @GetMapping("/health")
    @ApiOperation("Check health")
    public ResponseEntity<?> checkIfUserHasSubscription() {
        return ResponseEntity.ok(Health.up().build());
    }
}
