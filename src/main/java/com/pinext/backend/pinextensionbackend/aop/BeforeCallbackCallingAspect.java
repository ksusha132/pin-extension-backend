package com.pinext.backend.pinextensionbackend.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.pinext.backend.pinextensionbackend.callback.SubscriptionActivatedCallback;
import com.pinext.backend.pinextensionbackend.encpriprion.EncryptionService;
import com.pinext.backend.pinextensionbackend.exception.FailSecretCheckException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Slf4j
@Component
public class BeforeCallbackCallingAspect {

    public static final String X_FS_SIGNATURE = "X-FS-Signature";
    private final EncryptionService encryptionService;

    public BeforeCallbackCallingAspect(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Before(value = "@annotation(fastSpringApiRequested)")
    public void authenticate(JoinPoint joinPoint, FastSpringApiRequested fastSpringApiRequested) throws Exception {
        log.info("Checking secret....");
        SubscriptionActivatedCallback callback = (SubscriptionActivatedCallback) joinPoint.getArgs()[0];
        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(callback);
        checkHeader(getHeader(), body);
    }

    private void checkHeader(String header, String body) throws Exception {
        if (header == null) {
            throw new FailSecretCheckException("Secret in header is null ");
        }

        if (body == null) {
            throw new FailSecretCheckException("Body is missing ");
        }

        if (!encryptionService.isHashOk(header, body)) {
            log.error("Secret is not valid. " + header);
            throw new FailSecretCheckException("Secret in header is not valid " + header);
        }
        log.info("Checking secret passed succesfully");
    }

    private String getHeader() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getHeader(X_FS_SIGNATURE))
                .orElseThrow(() -> new FailSecretCheckException("Cannot get header"));
    }
}
