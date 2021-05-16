package com.pinext.backend.pinextensionbackend.service.impl;

import com.paymentwall.java.Pingback;
import com.pinext.backend.pinextensionbackend.service.PaymentWallService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Slf4j
public class PaymentWallServiceImpl implements PaymentWallService {
    @Override
    public String processCallback(HttpServletRequest request) {
        Pingback pingback = new Pingback(request.getParameterMap(), request.getRemoteAddr());
        if (pingback.validate()) {
            String goods = pingback.getProductId();
            String userId = pingback.getUserId();
            if (pingback.isDeliverable()) {
                // deliver Product to user with userId
            } else if (pingback.isCancelable()) {
                // withdraw Product from user with userId
            }
            return "OK";
        } else
            return pingback.getErrorSummary();
    }
}
