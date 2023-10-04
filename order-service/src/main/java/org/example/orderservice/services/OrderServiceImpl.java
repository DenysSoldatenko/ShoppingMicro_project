package org.example.orderservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.CustomerOrderDto;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing orders.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


  @Override
  public CustomerOrderDto addOrder(CustomerOrderDto orderDto) {
    return null;
  }

  @Override
  public AdminOrderDto getOrderById(long orderId) {
    return null;
  }
}
