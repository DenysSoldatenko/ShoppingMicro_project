package org.example.orderservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the OrderService.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class OrderServiceSecurityConfig {

  /**
   * Creates and configures an OAuth2AuthorizedClientManager bean using an
   * AuthorizedClientServiceOAuth2AuthorizedClientManager.
   *
   * @param clientRegistrationRepository    The repository containing client registrations.
   * @param auth2AuthorizedClientService The service for managing authorized client details.
   * @return An instance of OAuth2AuthorizedClientManager.
   */
  @Bean
  public OAuth2AuthorizedClientManager clientManager(
      ClientRegistrationRepository clientRegistrationRepository,
      OAuth2AuthorizedClientService auth2AuthorizedClientService
  ) {

    OAuth2AuthorizedClientProvider clientProvider
        = OAuth2AuthorizedClientProviderBuilder
        .builder()
        .clientCredentials()
        .build();

    AuthorizedClientServiceOAuth2AuthorizedClientManager clientManager
        = new AuthorizedClientServiceOAuth2AuthorizedClientManager(
        clientRegistrationRepository, auth2AuthorizedClientService
    );

    clientManager.setAuthorizedClientProvider(clientProvider);

    return clientManager;
  }

  /**
   * Configures the security filter chain for the OrderService.
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
          .anyRequest().authenticated()
      )
      .oauth2ResourceServer(
        resourceServerSpec -> resourceServerSpec
          .jwt(Customizer.withDefaults())
      )
      .build();
  }
}
