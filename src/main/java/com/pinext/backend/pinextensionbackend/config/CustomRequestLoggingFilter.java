package com.pinext.backend.pinextensionbackend.config;

import com.pinext.backend.pinextensionbackend.interceptptor.IncomingRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomRequestLoggingFilter implements WebMvcConfigurer {

    final
    IncomingRequestInterceptor incomingRequestInterceptor;

    public CustomRequestLoggingFilter(IncomingRequestInterceptor incomingRequestInterceptor) {
        this.incomingRequestInterceptor = incomingRequestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(incomingRequestInterceptor)
                .addPathPatterns("/**/api/v1/**/");
    }
}
