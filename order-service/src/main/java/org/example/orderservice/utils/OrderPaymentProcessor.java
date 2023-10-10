package org.example.orderservice.utils;

import static org.example.orderservice.entities.OrderStatus.FAILED;
import static org.example.orderservice.entities.OrderStatus.PLACED;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.entities.OrderStatus;
import org.example.orderservice.feign.PaymentService;
import org.example.orderservice.repositories.OrderRepository;
import org.example.paymentservice.dtos.PaymentDto;
import org.springframework.stereotype.Component;

/**
 * Utility class for processing payments associated with orders.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderPaymentProcessor {

  private final PaymentService paymentService;
  private final OrderRepository orderRepository;

  /**
   * Processes the payment for the given order and updates its status accordingly.
   *
   * @param order    The order to process the payment for.
   * @param orderDto The DTO containing order details.
   * @return The updated order.
   */
  public Order processPayment(Order order, RequestDto orderDto) {
    PaymentDto paymentRequest = getPaymentDto(order, orderDto);
    OrderStatus orderStatus = getOrderStatusAfterPayment(paymentRequest);

    order.setOrderStatus(orderStatus);
    orderRepository.save(order);

    log.info("Order placement completed for Order Id: {}."
        + "Order Status: {}", order.getId(), orderStatus);

    return order;
  }

  private static PaymentDto getPaymentDto(Order order, RequestDto orderDto) {
    return new PaymentDto(order.getId(), orderDto.paymentMethod(), orderDto.amount());
  }

  private OrderStatus getOrderStatusAfterPayment(PaymentDto paymentRequest) {
    try {
      paymentService.processPayment(paymentRequest);
      log.info("Payment successfully processed for Order Id: {}. "
          + "Changing the Order status to PLACED.", paymentRequest.orderId());
      return PLACED;
    } catch (Exception e) {
      log.error("Error occurred during payment processing for Order Id: {}. "
          + "Changing order status to FAILED.", paymentRequest.orderId(), e);
      return FAILED;
    }
  }
}
