package com.pinext.backend.pinextensionbackend.controller;

import com.pinext.backend.pinextensionbackend.callback.*;
import com.pinext.backend.pinextensionbackend.service.FastSpringService;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@Api("API FastSpring operation")
public class FastSpringController {
    public static final String BASE_PATH = "/api/v1";
    public static final String CALLBACK_SUBS_ACTIVATED = BASE_PATH + "/subscription-activated";
    public static final String CALLBACK_SUBS_CHARGE_COMPLETED = BASE_PATH + "/subscription-charge-completed";
    public static final String CALLBACK_SUBS_CHARGE_FAILED = BASE_PATH + "/subscription-charge-failed";
    public static final String CALLBACK_SUBS_DEACTIVATED = BASE_PATH + "/subscription-deactivated";

    final FastSpringService fastSpringService;

    public FastSpringController(FastSpringService fastSpringService) {
        this.fastSpringService = fastSpringService;
    }

    // @FastSpringApiRequested
    @SneakyThrows
    @PostMapping(CALLBACK_SUBS_ACTIVATED)
    public ResponseEntity<?> activateSubscription(@RequestBody MainRequest request) {
        return ResponseEntity.ok(fastSpringService.subscriptionActivated(getSubscriptionCallbackCommon(request)));
    }

    // @FastSpringApiRequested
    @PostMapping(CALLBACK_SUBS_DEACTIVATED)
    public ResponseEntity<?> subscriptionDeactivated(@RequestBody MainRequest request) {
        return ResponseEntity.ok(fastSpringService.subscriptionDeactivated(getSubscriptionCallbackCommon(request)));
    }


    //@FastSpringApiRequested
    @PostMapping(CALLBACK_SUBS_CHARGE_COMPLETED)
    public ResponseEntity<?> chargeCompleted(@RequestBody MainRequest request) {
        return ResponseEntity.ok(fastSpringService.chargeCompleted(getSubscriptionCallbackCommon(request)));
    }

    //@FastSpringApiRequested
    @PostMapping(CALLBACK_SUBS_CHARGE_FAILED)
    public ResponseEntity<?> subscriptionChargeFailed(@RequestBody MainRequest request) {
        return ResponseEntity.ok(fastSpringService.chargeFailed(getSubscriptionCallbackCommon(request)));
    }

    private SubscriptionCallbackCommon getSubscriptionCallbackCommon(@RequestBody MainRequest request) {
        return request.getEvents()
                .stream()
                .findFirst()
                .map(Event::getData)
                .orElseThrow(() -> new RuntimeException("cannot get event"));
    }
}
