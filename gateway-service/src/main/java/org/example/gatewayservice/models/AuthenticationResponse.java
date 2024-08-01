package org.example.gatewayservice.models;

import java.util.Collection;

/**
 * Represents an authentication response containing user and access token details.
 */
public record AuthenticationResponse(
    String userId,
    String accessToken,
    String refreshToken,
    long expiresAt,
    Collection<String> authorities
) {
}
