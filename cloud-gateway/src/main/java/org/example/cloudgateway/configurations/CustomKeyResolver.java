package org.example.cloudgateway.configurations;

import static reactor.core.publisher.Mono.just;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for custom key resolvers used in rate limiting.
 */
@Configuration
public class CustomKeyResolver {

  @Bean
  public KeyResolver userKeyResolver() {
    return exchange -> just("userKey");
  }
}
