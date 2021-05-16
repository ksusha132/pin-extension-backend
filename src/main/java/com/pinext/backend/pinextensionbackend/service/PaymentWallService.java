package com.pinext.backend.pinextensionbackend.service;

import javax.servlet.http.HttpServletRequest;

public interface PaymentWallService {
    String processCallback(HttpServletRequest request);
}
