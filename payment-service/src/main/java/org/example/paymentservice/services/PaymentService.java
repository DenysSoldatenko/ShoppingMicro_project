package org.example.paymentservice.services;

import org.example.paymentservice.dtos.PaymentDto;

/**
 * Service interface for managing payment-related operations.
 */
public interface PaymentService {
  PaymentDto processPayment(PaymentDto paymentDto);

  PaymentDto getPaymentDetailsByOrderId(long orderId);
}
