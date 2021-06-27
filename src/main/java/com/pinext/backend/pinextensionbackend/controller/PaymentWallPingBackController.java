package com.pinext.backend.pinextensionbackend.controller;

import com.pinext.backend.pinextensionbackend.dto.PingBackDto;
import com.pinext.backend.pinextensionbackend.request.PaymentUrlRequest;
import com.pinext.backend.pinextensionbackend.response.PaymentUrlResponse;
import com.pinext.backend.pinextensionbackend.service.PingBackService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
public class PaymentWallPingBackController {
    public static final String BASE_PATH = "/api/v1";
    public static final String PAYMENT_URL = BASE_PATH + "/payment-url";
    public static final String PING_BACK = BASE_PATH + "/pingback_path";

    private final PingBackService pingBackService;

    public PaymentWallPingBackController(PingBackService pingBackService) {
        this.pingBackService = pingBackService;
    }

    @ApiOperation("Generate payment ul")
    @PostMapping(PAYMENT_URL)
    public ResponseEntity<PaymentUrlResponse> generatePaymentUrl(
            @ApiParam(value = "Generate payment request.")
            @RequestBody PaymentUrlRequest request) {
        return ResponseEntity.ok(pingBackService.generatePaymentUrl(request));
    }

    @ApiOperation("Ping back")
    @GetMapping(PING_BACK)
    public ResponseEntity<String> checkIfUserHasSubscription(@RequestParam String uid,
                                                             @RequestParam String goodsid,
                                                             @RequestParam int slength,
                                                             @RequestParam String speriod,
                                                             @RequestParam int type,
                                                             @RequestParam String ref,
                                                             @RequestParam int sign_version,
                                                             @RequestParam String sig,
                                                             HttpServletRequest request) {
        PingBackDto pingBack = new PingBackDto();
        pingBack.setUid(uid);
        pingBack.setGoodsid(goodsid);
        pingBack.setSlength(slength);
        pingBack.setSperiod(speriod);
        pingBack.setType(type);
        pingBack.setRef(ref);
        pingBack.setSignVersion(sign_version);
        pingBack.setSig(sig);
        log.info("Payment wall pingback request came: {}", pingBack);

        return ResponseEntity.ok(pingBackService.acceptPingBack(pingBack, request));
    }
}
