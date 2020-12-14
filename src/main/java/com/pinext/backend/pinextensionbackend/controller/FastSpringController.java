package com.pinext.backend.pinextensionbackend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinext.backend.pinextensionbackend.aop.FastSpringApiRequested;
import com.pinext.backend.pinextensionbackend.callback.*;
import com.pinext.backend.pinextensionbackend.service.FastSpringService;
import io.micrometer.core.instrument.util.IOUtils;
import io.swagger.annotations.Api;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
    public ResponseEntity<?> activateSubscription(HttpServletRequest request) {
        ObjectMapper objectMapper = new ObjectMapper();
        String requestStr = IOUtils.toString(request.getInputStream());
        SubscriptionActivatedCallback callback = objectMapper.readValue(requestStr, SubscriptionActivatedCallback.class);
        return ResponseEntity.ok(fastSpringService.subscriptionActivated(callback));
    }

    // @FastSpringApiRequested
    @PostMapping(CALLBACK_SUBS_DEACTIVATED)
    public ResponseEntity<?> subscriptionDeactivated(@RequestBody SubscriptionDeactivatedCallback request) {
        return ResponseEntity.ok(fastSpringService.subscriptionDeactivated(request));
    }


    //@FastSpringApiRequested
    @PostMapping(CALLBACK_SUBS_CHARGE_COMPLETED)
    public ResponseEntity<?> chargeCompleted(@RequestBody SubscriptionChargeCompletedCallback request) {
        return ResponseEntity.ok(fastSpringService.chargeCompleted(request));
    }

    //@FastSpringApiRequested
    @PostMapping(CALLBACK_SUBS_CHARGE_FAILED)
    public ResponseEntity<?> subscriptionChargeFailed(@RequestBody SubscriptionChargeFailedCallback request) {
        return ResponseEntity.ok(fastSpringService.chargeFailed(request));
    }
}
