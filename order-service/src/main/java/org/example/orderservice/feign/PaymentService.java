package org.example.orderservice.feign;

import static org.example.orderservice.utils.MessageConstants.PAYMENT_SERVICE_UNAVAILABLE;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.orderservice.exceptions.OrderServiceException;
import org.example.orderservice.feign.models.PaymentDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client interface for communicating with the Payment Service.
 */
@FeignClient(name = "payment-service/api/v1/payments/")
@CircuitBreaker(name = "payment-service", fallbackMethod = "fallbackMethod")
public interface PaymentService {

  Logger logger = LoggerFactory.getLogger(PaymentService.class);

  @PostMapping("processPayment")
  ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentRequest);

  @GetMapping("{orderId}")
  ResponseEntity<PaymentDto> getPaymentDetailsByOrderId(@PathVariable long orderId);

  default ResponseEntity<PaymentDto> fallbackMethod(Exception e) {
    logger.error("Fallback method invoked due to exception: {}", e.getMessage());
    throw new OrderServiceException(PAYMENT_SERVICE_UNAVAILABLE, "UNAVAILABLE", "500");
  }
}
