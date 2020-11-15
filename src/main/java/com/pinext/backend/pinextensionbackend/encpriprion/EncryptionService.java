package com.pinext.backend.pinextensionbackend.encpriprion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionService {
    @Value("${encryption.key}")
    private String key;

    public Boolean isHashOk(String hashedString) {
        String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(key);
        return sha256hex.equalsIgnoreCase(hashedString);
    }
}
