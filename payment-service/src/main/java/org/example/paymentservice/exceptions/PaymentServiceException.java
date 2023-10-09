package org.example.paymentservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating a not found condition in the PaymentService.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PaymentServiceException extends RuntimeException {

  public PaymentServiceException(String message) {
    super(message);
  }
}
