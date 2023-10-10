package org.example.orderservice.services;

import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.dtos.RequestDto;

/**
 * Service interface for managing orders.
 */
public interface OrderService {
  OrderDto addOrder(RequestDto requestDto);

  AdminOrderDto getOrderById(long orderId);
}
