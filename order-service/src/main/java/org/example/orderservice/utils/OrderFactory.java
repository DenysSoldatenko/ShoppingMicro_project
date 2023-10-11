package org.example.orderservice.utils;

import static java.time.Instant.now;
import static org.example.orderservice.entities.OrderStatus.CREATED;

import lombok.experimental.UtilityClass;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.repositories.OrderRepository;

/**
 * Utility class for creating and saving Order instances.
 */
@UtilityClass
public class OrderFactory {

  /**
   * Creates and saves an Order based on the information provided in the OrderRequest DTO.
   *
   * @param orderRequest   The DTO containing information for creating the order.
   * @param orderRepository The repository for managing Order entities.
   * @return The created Order instance.
   */
  public Order createOrder(RequestDto orderRequest, OrderRepository orderRepository) {
    Order createdOrder = Order.builder()
        .amount(orderRequest.amount())
        .orderStatus(CREATED)
        .productId(orderRequest.productId())
        .orderDate(now())
        .quantity(orderRequest.quantity())
        .build();

    orderRepository.save(createdOrder);

    return createdOrder;
  }
}
