package org.example.orderservice.dtos;


import io.swagger.v3.oas.annotations.media.Schema;
import org.example.orderservice.feign.models.PaymentDto;
import org.example.orderservice.feign.models.ProductDto;

/**
 * Represents an administrative order DTO that includes order, product, and payment details.
 */
@Schema(description = "Represents an administrative order DTO that includes order, product, and payment details")
public record AdminOrderDto(

    @Schema(description = "Details of the order")
    OrderDto orderDetails,

    @Schema(description = "Details of the product associated with the order")
    ProductDetails productDetails,

    @Schema(description = "Details of the payment associated with the order")
    PaymentDetails paymentDetails
) {

  /**
   * Represents the product details included in the administrative order DTO.
   */
  @Schema(description = "Represents the product details included in the administrative order DTO")
  public record ProductDetails(

      @Schema(description = "The product data")
      ProductDto productData
  ) { }

  /**
   * Represents the payment details included in the administrative order DTO.
   */
  @Schema(description = "Represents the payment details included in the administrative order DTO")
  public record PaymentDetails(

      @Schema(description = "The payment data")
      PaymentDto paymentData
  ) { }
}
