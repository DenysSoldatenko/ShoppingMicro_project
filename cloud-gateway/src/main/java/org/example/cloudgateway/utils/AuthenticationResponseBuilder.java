package org.example.cloudgateway.utils;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import lombok.experimental.UtilityClass;
import org.example.cloudgateway.models.AuthenticationResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

/**
 * Utility class for building an AuthenticationResponse
 * from an OidcUser and OAuth2AuthorizedClient.
 */
@UtilityClass
public class AuthenticationResponseBuilder {

  /**
   * Builds an AuthenticationResponse from the provided OidcUser and OAuth2AuthorizedClient.
   *
   * @param oidcUser The OidcUser containing user details.
   * @param client   The OAuth2AuthorizedClient containing authorization details.
   * @return An AuthenticationResponse representing the authentication response.
   */
  public static AuthenticationResponse buildFromOidcUserAndOauth2Client(
      OidcUser oidcUser, OAuth2AuthorizedClient client
  ) {
    return new AuthenticationResponse(
      oidcUser.getEmail(),
      client.getAccessToken().getTokenValue(),
      requireNonNull(client.getRefreshToken()).getTokenValue(),
      requireNonNull(client.getAccessToken().getExpiresAt()).getEpochSecond(),
      oidcUser.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(toList())
    );
  }
}
