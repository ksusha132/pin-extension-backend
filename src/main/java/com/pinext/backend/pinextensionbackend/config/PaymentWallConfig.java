package com.pinext.backend.pinextensionbackend.config;

import com.paymentwall.java.Config;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class PaymentWallConfig {
    @PostConstruct
    public void init() {
        Config.getInstance().setLocalApiType(Config.API_GOODS);
        Config.getInstance().setPublicKey("aea088af75de6feb1da7358f976ec26c");
        Config.getInstance().setPrivateKey("91ece235b9995f8f38eb8c2d1cb0b9a5");
    }
}
