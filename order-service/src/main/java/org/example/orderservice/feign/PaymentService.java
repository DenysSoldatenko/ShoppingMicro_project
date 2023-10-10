package org.example.orderservice.feign;

import org.example.paymentservice.dtos.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign client interface for communicating with the Payment Service.
 */
@FeignClient(name = "payment-service/api/v1/payments/")
public interface PaymentService {

  @PostMapping("processPayment")
  ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentRequest);

}