package com.pinext.backend.pinextensionbackend.inicializer;

import com.pinext.backend.pinextensionbackend.filter.LoggingFilter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class CustomWebAppInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext container) {

        // get context
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.pinext.backend.pinextensionbackend");
        container.addListener(new ContextLoaderListener(context));

        ServletRegistration.Dynamic dispatcher
                = container.addServlet("dispatcher",
                new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");

        container.addFilter("customRequestLoggingFilter",
                LoggingFilter.class)
                .addMappingForServletNames(null, false, "dispatcher");
    }
}
