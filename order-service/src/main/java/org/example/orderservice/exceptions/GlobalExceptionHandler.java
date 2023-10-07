package org.example.orderservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static java.lang.Integer.parseInt;

/**
 * Global exception handler for OrderService exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handles custom exceptions from OrderService.
   *
   * @param exception  The exception to handle.
   * @param webRequest The current web request.
   * @return A ResponseEntity containing details of the error.
   */
  @ExceptionHandler(OrderServiceException.class)
  public ResponseEntity<ErrorDetails> handleProductServiceCustomException(
      OrderServiceException exception, WebRequest webRequest
  ) {

    ErrorDetails errorDetails = new ErrorDetails(
        new Date(),
        String.valueOf(exception.getStatus()),
        exception.getErrorCode(),
        exception.getMessage(),
        webRequest.getDescription(false).substring(4)
    );

    return new ResponseEntity<>(errorDetails, HttpStatus.valueOf(parseInt(exception.getStatus())));
  }
}
