package org.example.orderservice.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.AdminOrderDto.PaymentDetails;
import org.example.orderservice.dtos.AdminOrderDto.ProductDetails;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.exceptions.OrderServiceException;
import org.example.orderservice.feign.PaymentService;
import org.example.orderservice.feign.ProductService;
import org.example.paymentservice.dtos.PaymentDto;
import org.example.productservice.dtos.ProductDto;

/**
 * Utility class for creating AdminOrderDto instances.
 */
@Slf4j
@UtilityClass
public class AdminOrderFactory {

  /**
   * Creates an AdminOrderDto by fetching product and payment details using external services.
   *
   * @param paymentService  The service for fetching payment details.
   * @param productService  The service for fetching product details.
   * @param order           The order DTO containing information for creating the admin order.
   * @return The created AdminOrderDto.
   * @throws OrderServiceException If an error occurs during the creation of the admin order.
   */
  public static AdminOrderDto createAdminOrder(PaymentService paymentService,
                                               ProductService productService, OrderDto order) {
    log.info("Creating Admin Order for Order ID: {}", order.id());

    try {
      log.info("Invoking Product service to fetch the product for id: {}", order.productId());
      ProductDto productResponse = productService.getProductById(order.productId()).getBody();

      log.info("Getting payment information from the payment Service");
      PaymentDto paymentResponse = paymentService.getPaymentDetailsByOrderId(order.id()).getBody();

      return new AdminOrderDto(
          order,
          new ProductDetails(productResponse),
          new PaymentDetails(paymentResponse)
      );
    } catch (Exception e) {
      log.error("Error creating Admin Order for Order ID: {}", order.id(), e);
      throw new OrderServiceException("Error creating admin order for order id: "
        + order.id(), "ORDER_CREATION_FAILED", "500");
    }
  }
}
