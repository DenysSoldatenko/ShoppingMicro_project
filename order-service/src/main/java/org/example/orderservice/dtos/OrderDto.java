package org.example.orderservice.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.Instant;
import org.example.orderservice.entities.OrderStatus;

/**
 * Data Transfer Object (DTO) representing order information.
 */
@Schema(description = "Data Transfer Object representing order information")
public record OrderDto(

    @Schema(
      description = "The unique identifier of the order",
      example = "1001"
    )
    Long id,

    @Schema(
      description = "The ID of the product associated with the order",
      example = "2002"
    )
    long productId,

    @Schema(
      description = "The quantity of the product ordered",
      example = "3"
    )
    long quantity,

    @Schema(
      description = "The date and time when the order was placed",
      example = "2024-08-15T14:30:00Z"
    )
    Instant orderDate,

    @Schema(
      description = "The status of the order",
      example = "PLACED"
    )
    OrderStatus orderStatus,

    @Schema(
      description = "The total amount for the order",
      example = "5000"
    )
    long amount
) {
}
