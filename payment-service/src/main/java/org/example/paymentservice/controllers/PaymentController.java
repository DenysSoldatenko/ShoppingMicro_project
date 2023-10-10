package org.example.paymentservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.paymentservice.dtos.PaymentDto;
import org.example.paymentservice.services.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/v1/payments/")
public class PaymentController {

  private final PaymentService paymentService;

  @PostMapping("processPayment")
  public ResponseEntity<PaymentDto> processPayment(@RequestBody PaymentDto paymentRequest) {
    PaymentDto productResponse = paymentService.processPayment(paymentRequest);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }

  @GetMapping("{orderId}")
  public ResponseEntity<PaymentDto> getPaymentDetailsByOrderId(@PathVariable long orderId) {
    PaymentDto productResponse = paymentService.getPaymentDetailsByOrderId(orderId);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }
}
