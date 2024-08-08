package org.example.paymentservice.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.swagger.v3.oas.annotations.enums.SecuritySchemeType.HTTP;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
      contact = @Contact(
        name = "Denys Soldatenko",
        email = "john.doe@example.com",
        url = "https://example.com/about"
      ),
      title = "Payment API",
      version = "1.0",
      description = "Payment API Information",
      license = @License(
        name = "Example License",
        url = "https://example.com"
      ),
      termsOfService = "https://example.com/terms"
    ),
    servers = {
        @Server(
          url = "http://localhost:8082",
          description = "Local server"
        )
    }
)
@SecurityScheme(
    name = "Bearer Authentication",
    type = HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
public class SwaggerConfiguration {

  /**
   * Configures OpenAPI documentation with
   * security requirements for Bearer Authentication.
   *
   * @return The OpenAPI instance.
   */
  @Bean
  public OpenAPI openApi() {
    return new OpenAPI().addSecurityItem(
      new SecurityRequirement().addList("Bearer Authentication")
    );
  }
}