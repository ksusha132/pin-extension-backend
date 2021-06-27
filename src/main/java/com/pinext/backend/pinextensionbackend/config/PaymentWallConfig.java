package com.pinext.backend.pinextensionbackend.config;

import com.paymentwall.java.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PaymentWallConfig {
    @Value("${payment-wall.secret-key}")
    private String applicationKey;
    @Value("${payment-wall.project-key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Config.getInstance().setLocalApiType(Config.API_GOODS);
        Config.getInstance().setPublicKey(applicationKey);
        Config.getInstance().setPrivateKey(secretKey);
    }
}
