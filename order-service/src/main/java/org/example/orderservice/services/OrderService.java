package org.example.orderservice.services;

import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.CustomerOrderDto;

/**
 * Service interface for managing orders.
 */
public interface OrderService {
  CustomerOrderDto addOrder(CustomerOrderDto orderDto);

  AdminOrderDto getOrderById(long orderId);
}
