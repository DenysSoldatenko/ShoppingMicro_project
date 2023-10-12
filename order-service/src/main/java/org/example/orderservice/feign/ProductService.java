package org.example.orderservice.feign;

import org.example.orderservice.feign.models.ProductDto;
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
public interface ProductService {

  @PutMapping("{id}/reduceQuantity")
  ResponseEntity<ProductDto> reduceQuantity(@PathVariable("id") long productId,
                                            @RequestParam long quantity);

  @GetMapping("{id}")
  ResponseEntity<ProductDto> getProductById(@PathVariable("id") long productId);
}
