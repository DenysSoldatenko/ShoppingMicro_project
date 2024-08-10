package org.example.orderservice.controllers;

import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.dtos.AdminOrderDto;
import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.initializers.OrderDataInitializer;
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
@RequestMapping("/api/v1/orders")
@Tag(name = "Order Controller", description = "Operations related to orders")
public class OrderController {

  private final OrderService orderService;
  private final OrderDataInitializer orderDataInitializer;

  /**
   * Initializes order data in the database.
   *
   * @return a message indicating the success or failure of the data initialization.
   */
  @PostMapping("/initialize")
  @Operation(
      summary = "Initialize Order Data",
      description = "Initializes order data in the database"
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Order data initialized successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized access"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<String> initializeData() {
    return new ResponseEntity<>(orderDataInitializer.initData(), CREATED);
  }

  /**
   * Adds a new order based on the provided request data.
   *
   * @param orderDto the details of the order to be added
   * @return the added order as a DTO
   */
  @PostMapping
  @PreAuthorize("hasAuthority('Customer')")
  @Operation(
      summary = "Add New Order",
      description = "Adds a new order based on the provided request data"
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Order created successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized access"),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "400", description = "Invalid request data")
  })
  public ResponseEntity<OrderDto> addOrder(@RequestBody RequestDto orderDto) {
    OrderDto order = orderService.addOrder(orderDto);
    return new ResponseEntity<>(order, HttpStatus.CREATED);
  }

  /**
   * Retrieves detailed information about an order by its ID.
   *
   * @param orderId the ID of the order to be retrieved
   * @return the details of the order, including product and payment information
   */
  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('Admin')")
  @Operation(
      summary = "Get Order by ID",
      description = "Retrieves detailed information about an order by its ID"
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
    @ApiResponse(responseCode = "401", description = "Unauthorized access"),
    @ApiResponse(responseCode = "403", description = "Access denied"),
    @ApiResponse(responseCode = "404", description = "Order not found")
  })
  public AdminOrderDto getOrderById(@PathVariable("id") long orderId) {
    return orderService.getOrderById(orderId);
  }
}
