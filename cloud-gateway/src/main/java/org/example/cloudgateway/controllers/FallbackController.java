package org.example.cloudgateway.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for handling fallback responses when services are unavailable.
 */
@RestController
public class FallbackController {

  @GetMapping("/fallback/orderService")
  public String orderServiceFallback() {
    return "Order Service is currently unavailable. Please try again later.";
  }

  @GetMapping("/fallback/paymentService")
  public String paymentServiceFallback() {
    return "Payment Service is currently unavailable. Please try again later.";
  }

  @GetMapping("/fallback/productService")
  public String productServiceFallback() {
    return "Product Service is currently unavailable. Please try again later.";
  }
}
