package org.example.orderservice.utils;

import org.example.orderservice.dtos.CustomerOrderDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.entities.OrderStatus;

import java.time.Instant;

/**
 * Utility class for creating Order instances.
 */
public class OrderFactory {

  /**
   * Creates a new Order instance with the specified parameters.
   *
   * @param orderRequest The order request containing necessary information.
   * @return The created Order instance.
   */
  public static Order createOrder(CustomerOrderDto orderRequest) {
    return Order.builder()
      .amount(orderRequest.amount())
      .orderStatus(OrderStatus.CREATED)
      .productId(orderRequest.productId())
      .orderDate(Instant.now())
      .quantity(orderRequest.quantity())
      .build();
  }
}
