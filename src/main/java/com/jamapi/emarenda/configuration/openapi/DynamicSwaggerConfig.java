package com.jamapi.emarenda.configuration.openapi;

import lombok.AllArgsConstructor;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Goes over all endpoints and creates in individual OpenApi groups where each group contains only a
 * single endpoint. Every group is used to create a separate openapi yaml which then can be used to
 * show a UI with only a single endpoint. The purpose is to embed these individual endpoint OpenAPI
 * UIs into ISDs on confluence.
 */
@Configuration
@AllArgsConstructor
public class DynamicSwaggerConfig {

  private static final String[] PATHS_TO_SKIP = new String[] {"/employee/{userId}"};
  public static final String CONTROLLER_SUFFIX = "Controller";
  private RequestMappingHandlerMapping requestMappingHandlerMapping;

  @Bean
  public List<GroupedOpenApi> generateDynamicGroups() {
    // Get all handler methods (endpoint mappings)
    Map<RequestMappingInfo, HandlerMethod> handlerMethods =
        requestMappingHandlerMapping.getHandlerMethods();

    List<GroupedOpenApi> groupedOpenApis = new ArrayList<>();
    groupedOpenApis.add(GroupedOpenApi.builder().group("all").pathsToMatch("/**").build());

    handlerMethods.entrySet().stream()
        .filter(entry -> ((String) entry.getValue().getBean()).endsWith(CONTROLLER_SUFFIX))
        .filter(entry -> entry.getKey().getPathPatternsCondition() != null)
        .forEach(
            entry -> {
              HandlerMethod value = entry.getValue();
              RequestMappingInfo key = entry.getKey();

              String name =
                  withoutControllerSuffix((String) value.getBean())
                      + "_"
                      + value.getMethod().getName();
              String[] paths = getFilteredPaths(key);
              String method = getHttpMethod(key);
              if (method == null) {
                return;
              }

              String[] pathsToSkip = getPathsToSkip(paths);

              groupedOpenApis.add(
                  GroupedOpenApi.builder()
                      .group(name)
                      .pathsToMatch(paths)
                      .pathsToExclude(pathsToSkip)
                      .addOperationCustomizer(httpMethodCustomizer(method))
                      .build());
            });

    return groupedOpenApis;
  }

  private static String[] getPathsToSkip(String[] paths) {
    return Arrays.asList(paths).contains("/shared-profiles/{shared-profile-id}")
        ? new String[] {"/shared-profiles/manage"}
        : new String[] {};
  }

  private static String getHttpMethod(RequestMappingInfo mappingInfo) {
    return mappingInfo.getMethodsCondition().getMethods().stream()
        .findFirst()
        .map(RequestMethod::name)
        .orElse(null);
  }

  private static String[] getFilteredPaths(RequestMappingInfo mappingInfo) {
    return mappingInfo.getPathPatternsCondition().getPatternValues().stream()
        .filter(s -> !Arrays.asList(PATHS_TO_SKIP).contains(s))
        .toArray(String[]::new);
  }

  private static String withoutControllerSuffix(String bean) {
    return bean.substring(0, bean.length() - CONTROLLER_SUFFIX.length());
  }

  /** Customizer to filter operations based on HTTP method */
  private OperationCustomizer httpMethodCustomizer(String method) {
    return (operation, handlerMethod) -> {
      // Get the HTTP method from the operation metadata
      String httpMethod = extractHttpMethod(handlerMethod);
      if (!method.equalsIgnoreCase(httpMethod)) {
        return null; // Skip operations that don't match the desired method
      }
      return operation;
    };
  }

  private String extractHttpMethod(HandlerMethod handlerMethod) {
    if (handlerMethod.getMethodAnnotation(org.springframework.web.bind.annotation.GetMapping.class)
        != null) {
      return "GET";
    }

    if (handlerMethod.getMethodAnnotation(org.springframework.web.bind.annotation.PostMapping.class)
        != null) {
      return "POST";
    }

    if (handlerMethod.getMethodAnnotation(org.springframework.web.bind.annotation.PutMapping.class)
        != null) {
      return "PUT";
    }

    if (handlerMethod.getMethodAnnotation(
            org.springframework.web.bind.annotation.DeleteMapping.class)
        != null) {
      return "DELETE";
    }

    return null; // Default to null if no mapping - shouldn't ever happen
  }
}
