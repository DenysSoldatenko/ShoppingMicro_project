package org.example.orderservice.security;

import static java.util.Objects.requireNonNull;
import static org.springframework.security.oauth2.client.OAuth2AuthorizeRequest.withClientRegistrationId;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;

/**
 * Feign request interceptor for adding OAuth2 Authorization header to outgoing requests.
 */
@Configuration
@RequiredArgsConstructor
public class OauthRequestInterceptor implements RequestInterceptor {

  private final OAuth2AuthorizedClientManager authorizedClientManager;

  @Override
  public void apply(RequestTemplate template) {
    template.header("Authorization", "Bearer " + requireNonNull(
        authorizedClientManager.authorize(
          withClientRegistrationId("internal-client")
            .principal("internal")
            .build()
        )
      ).getAccessToken()
        .getTokenValue()
    );
  }
}