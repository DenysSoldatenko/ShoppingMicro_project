package org.example.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating a not found condition in the OrderService.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class OrderServiceException extends RuntimeException {

  public OrderServiceException(String message) {
    super(message);
  }
}
