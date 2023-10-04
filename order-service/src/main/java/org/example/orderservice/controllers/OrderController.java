package org.example.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.CustomerOrderDto;
import org.example.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders/")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<CustomerOrderDto> addOrder(@RequestBody CustomerOrderDto orderDto) {
    CustomerOrderDto order = orderService.addOrder(orderDto);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  @GetMapping("{id}")
  public ResponseEntity<AdminOrderDto> getOrderById(@PathVariable("id") long orderId) {
    AdminOrderDto order = orderService.getOrderById(orderId);
    return new ResponseEntity<>(order, HttpStatus.OK);
  }
}
