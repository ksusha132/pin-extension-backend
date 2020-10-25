package com.pinext.backend.pinextensionbackend.controller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<?> checkIfUserHasSubscription() {
        return ResponseEntity.ok(Health.up().build());
    }
}
