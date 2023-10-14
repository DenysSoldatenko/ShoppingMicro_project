package org.example.cloudgateway.configurations;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * Configuration class for custom key resolvers used in rate limiting.
 */
@Configuration
public class CustomKeyResolver {

  @Bean
  public KeyResolver userKeyResolver() {
    return exchange -> Mono.just("userKey");
  }
}
