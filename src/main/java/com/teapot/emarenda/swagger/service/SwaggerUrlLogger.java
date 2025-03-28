package com.teapot.emarenda.swagger.service;

import com.teapot.emarenda.swagger.properties.SwaggerProperties;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SwaggerUrlLogger {
    private static final Logger logger = LoggerFactory.getLogger(SwaggerUrlLogger.class);
    
    @Value("${server.port}")
    private String serverPort;
    
    private final SwaggerProperties swaggerProperties;

    @PostConstruct
    public void logSwaggerUrls() {
        String baseUrl = "http://localhost:" + serverPort;
        logger.info("Swagger UI is available at: {}{}", baseUrl, swaggerProperties.getPath());
        if (!swaggerProperties.getUrls().isEmpty()) {
            swaggerProperties.getUrls().forEach(url -> 
                logger.info("OpenAPI specification is available at: {}{}", baseUrl, url.getUrl())
            );
        }
    }
} 