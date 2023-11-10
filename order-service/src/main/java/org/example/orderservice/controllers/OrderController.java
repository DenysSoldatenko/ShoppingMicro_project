package org.example.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing order-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders/")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @PreAuthorize("hasAuthority('Customer')")
  public ResponseEntity<OrderDto> addOrder(@RequestBody RequestDto orderDto) {
    OrderDto order = orderService.addOrder(orderDto);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer')")
  public ResponseEntity<AdminOrderDto> getOrderById(@PathVariable("id") long orderId) {
    AdminOrderDto order = orderService.getOrderById(orderId);
    return new ResponseEntity<>(order, HttpStatus.OK);
  }
}
