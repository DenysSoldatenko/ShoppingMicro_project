package org.example.productservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.productservice.dtos.ProductDto;
import org.example.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing product-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products/")
public class ProductController {

  private final ProductService productService;

  @PostMapping
  public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productRequest) {
    ProductDto product = productService.addProduct(productRequest);
    return new ResponseEntity<>(product, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<ProductDto> getProductById(@PathVariable("id") long productId) {
    ProductDto productResponse = productService.getProductById(productId);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }

  @PutMapping("{id}/reduceQuantity")
  public ResponseEntity<ProductDto> reduceQuantity(@PathVariable("id") long productId,
                                             @RequestParam(name = "quantity") int quantity) {
    ProductDto productResponse = productService.reduceProductQuantity(productId, quantity);
    return new ResponseEntity<>(productResponse, HttpStatus.OK);
  }
}
