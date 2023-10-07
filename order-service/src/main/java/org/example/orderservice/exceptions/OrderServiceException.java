package org.example.orderservice.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderServiceException extends RuntimeException {

  private final String errorCode;
  private final String status;

  public OrderServiceException(String message, String errorCode, String status) {
    super(message);
    this.errorCode = errorCode;
    this.status = status;
  }
}
