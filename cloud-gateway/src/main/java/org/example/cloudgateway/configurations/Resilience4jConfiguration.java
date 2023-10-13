package org.example.cloudgateway.configurations;

import static io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.ofDefaults;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Resilience4j circuit breaker settings.
 */
@Configuration
public class Resilience4jConfiguration {

  /**
   * Provides a customizer for configuring the Resilience4j
   * circuit breaker factory with default settings.
   *
   * @return A customizer for the Resilience4j circuit breaker factory.
   */
  @Bean
  public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {
    return factory -> factory.configureDefault(
      id -> new Resilience4JConfigBuilder(id)
        .circuitBreakerConfig(ofDefaults())
        .build());
  }
}

