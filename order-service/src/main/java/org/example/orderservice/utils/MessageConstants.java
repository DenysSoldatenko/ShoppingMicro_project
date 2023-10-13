package org.example.orderservice.utils;

import lombok.experimental.UtilityClass;

/**
 * Class containing constants for messages used in the OrderService.
 */
@UtilityClass
public class MessageConstants {
  public static final String ORDER_NOT_FOUND = "Order not found for the order id: ";
  public static final String PAYMENT_SERVICE_UNAVAILABLE = "Payment Service is not available";
  public static final String PRODUCT_SERVICE_UNAVAILABLE = "Product Service is not available";
}
