package org.example.orderservice.feign;

import static org.example.orderservice.utils.MessageConstants.PRODUCT_SERVICE_UNAVAILABLE;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.orderservice.exceptions.OrderServiceException;
import org.example.orderservice.feign.models.ProductDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Feign client interface for communicating with the Product Service.
 */
@FeignClient(name = "product-service/api/v1/products/")
@CircuitBreaker(name = "product-service", fallbackMethod = "fallbackMethod")
public interface ProductService {

  Logger logger = LoggerFactory.getLogger(PaymentService.class);

  @PutMapping("{id}/reduceQuantity")
  ResponseEntity<ProductDto> reduceQuantity(@PathVariable("id") long productId,
                                            @RequestParam long quantity);

  @GetMapping("{id}")
  ResponseEntity<ProductDto> getProductById(@PathVariable("id") long productId);

  default ResponseEntity<ProductDto> fallbackMethod(Exception e) {
    logger.error("Fallback method invoked due to exception: {}", e.getMessage());
    throw new OrderServiceException(PRODUCT_SERVICE_UNAVAILABLE, "UNAVAILABLE", "500");
  }
}
