package org.example.orderservice.services;

import static org.example.orderservice.utils.OrderFactory.createOrder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.CustomerOrderDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.feign.ProductService;
import org.example.orderservice.mappers.OrderMapper;
import org.example.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing orders.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  private final ProductService productService;

  private final OrderMapper orderMapper;

  @Override
  public CustomerOrderDto addOrder(CustomerOrderDto orderDto) {
    productService.reduceQuantity(orderDto.productId(), orderDto.quantity());
    Order order = createOrder(orderDto);
    orderRepository.save(order);
    return orderMapper.toDto(order);
  }

  @Override
  public AdminOrderDto getOrderById(long orderId) {
    return null;
  }
}
