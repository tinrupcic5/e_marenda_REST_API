package com.teapot.emarenda.swagger.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "springdoc.swagger-ui")
public class SwaggerProperties {
    private String path;
    private String operationsSorter;
    private String tagsSorter;
    private boolean tryItOutEnabled;
    private boolean filter;
    private List<SwaggerUrl> urls;
    private boolean showActuator;

    @Getter
    @Setter
    public static class SwaggerUrl {
        private String url;
        private String name;
    }
} 