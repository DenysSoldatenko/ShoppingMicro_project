package org.example.orderservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Custom exception class for Order Service-related errors.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderServiceException extends RuntimeException {

  private final String errorCode;
  private final String status;

  /**
   * Constructs a new OrderServiceException with the specified message, error code, and status.
   *
   * @param message   The detail message.
   * @param errorCode The error code associated with the exception.
   * @param status    The status associated with the exception.
   */
  public OrderServiceException(String message, String errorCode, String status) {
    super(message);
    this.errorCode = errorCode;
    this.status = status;
  }
}
