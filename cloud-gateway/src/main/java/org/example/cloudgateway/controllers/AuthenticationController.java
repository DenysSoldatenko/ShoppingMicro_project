package org.example.cloudgateway.controllers;

import static org.example.cloudgateway.utils.AuthenticationResponseBuilder.buildFromOidcUserAndOauth2Client;

import org.example.cloudgateway.models.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<AuthenticationResponse> login(
      @AuthenticationPrincipal OidcUser oidcUser,
      @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client
  ) {
    AuthenticationResponse response = buildFromOidcUserAndOauth2Client(oidcUser, client);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}