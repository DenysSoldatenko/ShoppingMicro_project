package org.example.orderservice.feign.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing payment information.
 */
@Schema(description = "Data Transfer Object representing payment information")
public record PaymentDto(

    @Schema(
      description = "The ID of the associated order",
      example = "1"
    )
    Long orderId,

    @Schema(
      description = "The method of payment",
      example = "CREDIT_CARD"
    )
    PaymentMethod paymentMethod,

    @Schema(
      description = "The amount paid for the order",
      example = "2500"
    )
    Long amount
) {
}
