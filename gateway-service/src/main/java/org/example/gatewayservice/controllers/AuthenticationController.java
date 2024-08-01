package org.example.gatewayservice.controllers;

import static org.example.gatewayservice.utils.AuthenticationResponseBuilder.buildFromOidcUserAndOauth2Client;

import org.example.gatewayservice.models.AuthenticationResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling authentication-related endpoints.
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

  @GetMapping("/login")
  public AuthenticationResponse login(
      @AuthenticationPrincipal OidcUser oidcUser,
      @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
  ) {
    return buildFromOidcUserAndOauth2Client(oidcUser, client);
  }
}