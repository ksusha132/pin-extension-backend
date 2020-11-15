package com.pinext.backend.pinextensionbackend.aop;

import com.pinext.backend.pinextensionbackend.encpriprion.EncryptionService;
import com.pinext.backend.pinextensionbackend.exception.FailSecretCheckException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Optional;

@Aspect
@Component
public class BeforeCallbackCallingAspect {

    public static final String X_FS_SIGNATURE = "X-FS-Signature";
    private final EncryptionService encryptionService;

    public BeforeCallbackCallingAspect(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }

    @Before(value = "@annotation(fastSpringApiRequested)")
    public void authenticate(JoinPoint joinPoint, FastSpringApiRequested fastSpringApiRequested) {
        checkHeader(getHeader());
    }

    private void checkHeader(String header) {
        if (header == null) {
            throw new FailSecretCheckException("Secret in header is null ");
        }

        if (!encryptionService.isHashOk(header)) {
            throw new FailSecretCheckException("Secret in header is not valid " + header);
        }
    }

    private String getHeader() {
        return Optional.ofNullable(RequestContextHolder.getRequestAttributes())
                .map(requestAttributes -> (ServletRequestAttributes) requestAttributes)
                .map(ServletRequestAttributes::getRequest)
                .map(request -> request.getHeader(X_FS_SIGNATURE))
                .orElseThrow(() -> new FailSecretCheckException("Cannot get header"));
    }
}
