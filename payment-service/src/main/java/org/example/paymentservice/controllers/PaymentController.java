package org.example.paymentservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dtos.PaymentDto;
import org.example.paymentservice.services.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing order-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("/processPayment")
  public PaymentDto processPayment(@RequestBody PaymentDto paymentRequest) {
    return paymentService.processPayment(paymentRequest);
  }

  @GetMapping("/{orderId}")
  public PaymentDto getPaymentDetailsByOrderId(@PathVariable long orderId) {
    return paymentService.getPaymentDetailsByOrderId(orderId);
  }
}
