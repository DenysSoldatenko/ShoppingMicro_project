package org.example.productservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception indicating a not found condition in the ProductService.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductServiceException extends RuntimeException {

  public ProductServiceException(String message) {
    super(message);
  }
}
