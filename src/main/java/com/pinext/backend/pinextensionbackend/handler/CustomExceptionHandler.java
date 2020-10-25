package com.pinext.backend.pinextensionbackend.handler;

import com.pinext.backend.pinextensionbackend.exception.SubscriptionException;
import com.pinext.backend.pinextensionbackend.exception.UserNotFoundException;
import com.pinext.backend.pinextensionbackend.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
@Slf4j
@RestControllerAdvice({"com.pinext.backend.pinextbackend"})
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SubscriptionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<CommonResponse> handleSubscriptionException(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Exception e) {
        return logAndCreateHttpEntity(request, e, "404");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<CommonResponse> handleUserNotFoundException(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Exception e) {
        return logAndCreateHttpEntity(request, e, "404");
    }

    private HttpEntity<CommonResponse> logAndCreateHttpEntity(HttpServletRequest request, Throwable ex, String code) {
        log.error(request.getRequestURI(), ex.toString(), ex);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setError(ex.toString());
        commonResponse.setCode(code);
        return new HttpEntity<>(commonResponse);
    }
}
