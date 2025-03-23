package com.jamapi.emarenda.configuration.openapi;

import com.jamapi.emarenda.exception.IPErrorResponse;
import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    String securitySchemeName = "bearerAuth";
    Schema<?> ipErrResponseSchema = getSchema(IPErrorResponse.class);

    return new OpenAPI()
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(
            new Components()
                .addSchemas(
                    ipErrResponseSchema.getName(),
                    ipErrResponseSchema) // explicitly add so it can be referenced via ref
                .addSecuritySchemes(
                    securitySchemeName,
                    new SecurityScheme()
                        .name(securitySchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")));
  }

  private Schema<?> getSchema(Class<?> className) {
    return ModelConverters.getInstance()
        .resolveAsResolvedSchema(new AnnotatedType(className))
        .schema;
  }

  @Bean
  public GlobalOperationCustomizer globalOperationCustomizer() {
    return (operation, handlerMethod) -> {
      ApiResponse badRequestResponse =
          new ApiResponse()
              .description(
                "An error occurred. <br>"
                      + "4** (issue with caller, authorisation, etc.), <br>"
                      + "500 Internal Server Error (issue with individuals BE), <br>"
                      + "502 Bad Gateway (issue with external service)")
              .content(
                  new Content()
                      .addMediaType(
                          "application/json",
                          new MediaType()
                              .schema(new Schema<>().$ref("#/components/schemas/IPErrResponse"))));
      // Add the 400 response to all endpoints
      operation.getResponses().addApiResponse("400, 500, 502", badRequestResponse);
      return operation;
    };
  }

  /**
   * Used to append custom css to /swagger-ui/swagger-ui.css. Basically overrides the css used by
   * swagger-ui/index.html
   */
  @RestController
  @RequestMapping(path = "/swagger-ui")
  public static class SwaggerController {
    @GetMapping(path = "/swagger-ui.css", produces = "text/css")
    public String getCss() throws IOException {
      // get the original css
      String orig =
          new PathMatchingResourcePatternResolver()
              .getResources("classpath*:META-INF/**/swagger-ui.css")[0].getContentAsString(
                  StandardCharsets.UTF_8);

      // get the custom css to append.
      String append = toText(getClass().getResourceAsStream("/swagger-ui/custom-swagger-ui.css"));
      return orig + append;
    }

    static String toText(InputStream in) {
      return new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))
          .lines()
          .collect(Collectors.joining("\n"));
    }
  }
}
