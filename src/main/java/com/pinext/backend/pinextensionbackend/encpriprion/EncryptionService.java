//package com.pinext.backend.pinextensionbackend.encpriprion;
//
//import org.apache.commons.codec.binary.Hex;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.nio.charset.StandardCharsets;
//
//@Component
//public class EncryptionService {
//    @Value("${encryption.key}")
//    private String key;
//
//    public Boolean isHashOk(String hashedString, String request) throws Exception {
//        String encodedStr = encode(key, request);
//        return encodedStr.equalsIgnoreCase(hashedString);
//    }
//
//    public static String encode(String key, String data) throws Exception {
//        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
//        SecretKeySpec secret_key = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
//        sha256_HMAC.init(secret_key);
//        return Hex.encodeHexString(sha256_HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8)));
//    }
//}
