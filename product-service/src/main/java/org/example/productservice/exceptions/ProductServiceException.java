package org.example.productservice.exceptions;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating a not found condition in the ProductService.
 */
@ResponseStatus(NOT_FOUND)
public class ProductServiceException extends RuntimeException {

  public ProductServiceException(String message) {
    super(message);
  }
}
