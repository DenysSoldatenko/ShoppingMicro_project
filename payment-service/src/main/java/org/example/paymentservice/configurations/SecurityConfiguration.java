package org.example.paymentservice.configurations;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the PaymentService.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private static final String[] PUBLIC_ROUTES = {
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/swagger-resources/**",
    "/swagger-ui.html",
    "/webjars/**"
  };

  /**
   * Configures the security filter chain for the PaymentService.
   *
   * @param http The HttpSecurity instance to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public SecurityFilterChain securityWebFilterChain(HttpSecurity http) throws Exception {
    return http
      .authorizeHttpRequests(
        authorizeRequest -> authorizeRequest
          .requestMatchers(PUBLIC_ROUTES).permitAll()
          .anyRequest().authenticated()
      )
      .oauth2ResourceServer(
        serverConfigurer ->
          serverConfigurer
            .jwt(withDefaults())
      )
      .build();
  }
}
