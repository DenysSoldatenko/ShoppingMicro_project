package org.example.orderservice.utils;

import static java.time.Instant.now;
import static org.example.orderservice.entities.OrderStatus.CREATED;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Component;

/**
 * Utility class for creating and saving Order instances.
 */
@Component
@RequiredArgsConstructor
public class OrderFactory {

  private final OrderRepository orderRepository;

  /**
   * Creates a new Order instance with the specified parameters and saves it to the repository.
   *
   * @param orderRequest The order request containing necessary information.
   * @return The created and saved Order instance.
   */
  public Order createOrder(RequestDto orderRequest) {
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
