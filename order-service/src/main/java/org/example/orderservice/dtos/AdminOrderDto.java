package org.example.orderservice.dtos;


import org.example.orderservice.feign.models.PaymentDto;
import org.example.orderservice.feign.models.ProductDto;

/**
 * Represents an administrative order DTO that includes order, product, and payment details.
 */
public record AdminOrderDto(OrderDto orderDetails,
                            ProductDetails productDetails,
                            PaymentDetails paymentDetails) {

  /**
   * Represents the product details included in the administrative order DTO.
   */
  public record ProductDetails(ProductDto productData) { }

  /**
   * Represents the payment details included in the administrative order DTO.
   */
  public record PaymentDetails(PaymentDto paymentData) { }
}
