package org.example.orderservice.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.exceptions.OrderServiceException;
import org.example.orderservice.feign.PaymentService;
import org.example.orderservice.feign.ProductService;
import org.example.orderservice.mappers.OrderMapper;
import org.example.orderservice.repositories.OrderRepository;
import org.example.orderservice.utils.AdminOrderFactory;
import org.example.orderservice.utils.MessageConstants;
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

  private final OrderRepository orderRepository;

  private final ProductService productService;

  private final PaymentService paymentService;

  private final OrderMapper orderMapper;

  @Override
  public OrderDto addOrder(RequestDto requestDto) {
    productService.reduceQuantity(requestDto.productId(), requestDto.quantity());
    Order createdOrder = OrderFactory.createOrder(requestDto, orderRepository);
    Order updatedOrder = OrderPaymentProcessor.processPayment(createdOrder, requestDto, paymentService, orderRepository);
    return orderMapper.toDto(updatedOrder);
  }

  @Override
  public AdminOrderDto getOrderById(long orderId) {
    Order order = orderRepository.findById(orderId)
        .orElseThrow(() -> new OrderServiceException(MessageConstants.ORDER_NOT_FOUND + orderId,
          "NOT_FOUND", "404"));

    return AdminOrderFactory.createAdminOrder(paymentService, productService, orderMapper.toDto(order));
  }
}
