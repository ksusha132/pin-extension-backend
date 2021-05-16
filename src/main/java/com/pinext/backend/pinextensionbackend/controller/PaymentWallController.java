package com.pinext.backend.pinextensionbackend.controller;


import com.pinext.backend.pinextensionbackend.service.PaymentWallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.pinext.backend.pinextensionbackend.constans.Constants.PINGBACK;

@RestController
@Api("Processing paymentwall pingback.")
public class PaymentWallController {

    private final PaymentWallService paymentWallService;

    public PaymentWallController(PaymentWallService paymentWallService) {
        this.paymentWallService = paymentWallService;
    }

    @ApiOperation("Payment wall")
    @GetMapping(PINGBACK)
    public ResponseEntity<String> processPingBack(HttpServletRequest request) {
        return ResponseEntity.ok(paymentWallService.processCallback(request));
    }
}
