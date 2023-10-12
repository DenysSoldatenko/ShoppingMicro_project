package org.example.orderservice.utils;

import static org.example.orderservice.entities.OrderStatus.FAILED;
import static org.example.orderservice.entities.OrderStatus.PLACED;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.entities.Order;
import org.example.orderservice.entities.OrderStatus;
import org.example.orderservice.feign.PaymentService;
import org.example.orderservice.feign.models.PaymentDto;
import org.example.orderservice.repositories.OrderRepository;


/**
 * Utility class for processing payments and updating order status.
 */
@Slf4j
@UtilityClass
public class OrderPaymentProcessor {

  /**
   * Processes payment for the given order, updates its status, and logs the result.
   *
   * @param order           The order to be processed.
   * @param orderDto        The DTO containing information about the order.
   * @param paymentService  The service responsible for payment processing.
   * @param orderRepository The repository for managing Order entities.
   * @return The updated Order instance.
   */
  public Order processPayment(Order order, RequestDto orderDto,
                              PaymentService paymentService, OrderRepository orderRepository) {
    PaymentDto paymentRequest = getPaymentDto(order, orderDto);
    OrderStatus orderStatus = getOrderStatusAfterPayment(paymentRequest, paymentService);

    order.setOrderStatus(orderStatus);
    orderRepository.save(order);

    log.info("Order placement completed for Order Id: {}."
        + "Order Status: {}", order.getId(), orderStatus);

    return order;
  }

  private static PaymentDto getPaymentDto(Order order, RequestDto orderDto) {
    return new PaymentDto(order.getId(), orderDto.paymentMethod(), orderDto.amount());
  }

  private OrderStatus getOrderStatusAfterPayment(PaymentDto paymentRequest,
                                                 PaymentService paymentService) {
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
