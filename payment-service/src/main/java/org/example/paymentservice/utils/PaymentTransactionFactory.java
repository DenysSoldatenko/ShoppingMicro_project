package org.example.paymentservice.utils;

import static java.time.Instant.now;
import static org.example.paymentservice.entities.PaymentStatus.SUCCESS;

import lombok.experimental.UtilityClass;
import org.example.paymentservice.dtos.PaymentDto;
import org.example.paymentservice.entities.PaymentTransaction;

/**
 * Utility class for creating instances of PaymentTransaction from PaymentDto.
 */
@UtilityClass
public class PaymentTransactionFactory {

  /**
   * Creates a PaymentTransaction instance from a PaymentDto.
   *
   * @param paymentDto The PaymentDto to create the PaymentTransaction from.
   * @return A new PaymentTransaction instance.
   */
  public static PaymentTransaction createPaymentTransaction(PaymentDto paymentDto) {
    return PaymentTransaction.builder()
      .paymentDate(now())
      .paymentMethod(paymentDto.paymentMethod().name())
      .paymentStatus(SUCCESS)
      .orderId(paymentDto.orderId())
      .amount(paymentDto.amount())
      .build();
  }
}

