package org.example.orderservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.orderservice.feign.models.PaymentMethod;

/**
 * Represents a data transfer object (DTO) for processing order requests.
 */
@Schema(description = "Data Transfer Object for processing order requests")
public record RequestDto(

    @Schema(
      description = "The ID of the product being ordered",
      example = "1001"
    )
    long productId,

    @Schema(
      description = "The amount to be paid for the order",
      example = "5000"
    )
    long amount,

    @Schema(
      description = "The quantity of the product ordered",
      example = "3"
    )
    long quantity,

    @Schema(
      description = "The method of payment",
      example = "CREDIT_CARD"
    )
    PaymentMethod paymentMethod
) {
}
