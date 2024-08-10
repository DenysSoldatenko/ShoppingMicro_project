package org.example.orderservice.feign.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object (DTO) representing product information.
 */
@Schema(description = "Data Transfer Object representing product information")
public record ProductDto(

    @Schema(hidden = true)
    long id,

    @Schema(
      description = "Name of the product",
      example = "Smartphone"
    )
    String productName,

    @Schema(
      description = "Quantity of the product available",
      example = "100"
    )
    long quantity,

    @Schema(
      description = "Price of the product",
      example = "299"
    )
    long price
) {
}
