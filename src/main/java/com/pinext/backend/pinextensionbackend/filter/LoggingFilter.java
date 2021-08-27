package com.pinext.backend.pinextensionbackend.filter;

import org.springframework.web.filter.CommonsRequestLoggingFilter;

public class LoggingFilter extends CommonsRequestLoggingFilter {
    public LoggingFilter() {
        super.setIncludeQueryString(true);
        super.setIncludePayload(true);
        super.setMaxPayloadLength(10000);
    }
}
