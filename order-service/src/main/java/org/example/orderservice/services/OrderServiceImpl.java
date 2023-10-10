package org.example.orderservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.feign.ProductService;
import org.example.orderservice.mappers.OrderMapper;
import org.example.orderservice.utils.OrderFactory;
import org.example.orderservice.utils.OrderPaymentProcessor;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing orders.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

  private final OrderPaymentProcessor orderPaymentProcessor;

  private final OrderFactory orderFactory;

  private final ProductService productService;

  private final OrderMapper orderMapper;

  @Override
  public OrderDto addOrder(RequestDto requestDto) {
    productService.reduceQuantity(requestDto.productId(), requestDto.quantity());
    Order createdOrder = orderFactory.createOrder(requestDto);
    Order processedOrder = orderPaymentProcessor.processPayment(createdOrder, requestDto);
    return orderMapper.toDto(processedOrder);
  }

  @Override
  public AdminOrderDto getOrderById(long orderId) {
    return null;
  }
}
