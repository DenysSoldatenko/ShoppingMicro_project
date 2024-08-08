package org.example.paymentservice.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Date;

/**
 * Represents details of an error response.
 */
@Schema(description = "Details about the error response")
public record ErrorDetails(

    @Schema(
      description = "The timestamp when the error occurred",
      example = "2024-08-14T11:52:55.628+00:00"
    )
    Date timestamp,

    @Schema(
      description = "HTTP status code of the error",
      example = "404"
    )
    String status,

    @Schema(
      description = "Short description of the error",
      example = "Not Found"
    )
    String error,

    @Schema(
      description = "Detailed error message",
      example = "Payment details not found for order id: 123"
    )
    String message,

    @Schema(
      description = "The path where the error occurred",
      example = "/api/v1/payments/123"
    )
    String path
) {
}