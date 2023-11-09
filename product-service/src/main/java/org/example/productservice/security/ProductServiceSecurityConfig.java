package org.example.productservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the ProductService.
 */
@Configuration
@EnableWebSecurity
public class ProductServiceSecurityConfig {

  /**
   * Configures the security filter chain for the ProductService.
   *
   * @param http The HttpSecurity instance to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(
        authorizeRequests -> authorizeRequests
          .requestMatchers("/api/v1/products/**")
          .hasAuthority("SCOPE_internal")
          .anyRequest()
          .authenticated()
      )
      .oauth2ResourceServer(
        resourceServerSpec -> resourceServerSpec
          .jwt(Customizer.withDefaults())
      )
      .build();
  }
}
