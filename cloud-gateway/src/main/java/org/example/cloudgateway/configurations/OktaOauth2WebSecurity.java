package org.example.cloudgateway.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Configuration class for setting up Okta OAuth2-based security for application.
 */
@Configuration
@EnableWebFluxSecurity
public class OktaOauth2WebSecurity {

  /**
   * Configures the security filter chain with Okta OAuth2 settings.
   *
   * @param http The ServerHttpSecurity instance to configure.
   * @return The configured SecurityWebFilterChain.
   */
  @Bean
  public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
    return http
      .authorizeExchange(
        authorizeExchangeSpec -> authorizeExchangeSpec
          .anyExchange().authenticated()
      )
      .oauth2Login(Customizer.withDefaults())
      .oauth2ResourceServer(
        resourceServerSpec -> resourceServerSpec
          .jwt(Customizer.withDefaults())
      )
      .build();
  }
}