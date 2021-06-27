package com.pinext.backend.pinextensionbackend.service;

import com.pinext.backend.pinextensionbackend.dto.PingBackDto;
import com.pinext.backend.pinextensionbackend.request.PaymentUrlRequest;
import com.pinext.backend.pinextensionbackend.response.PaymentUrlResponse;

import javax.servlet.http.HttpServletRequest;

public interface PingBackService {
    PaymentUrlResponse generatePaymentUrl(PaymentUrlRequest request);

    String acceptPingBack(PingBackDto pingBack, HttpServletRequest request);
}
