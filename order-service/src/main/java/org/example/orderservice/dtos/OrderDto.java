package org.example.orderservice.dtos;

import java.time.Instant;
import org.example.orderservice.entities.OrderStatus;

/**
 * Data Transfer Object (DTO) representing order information.
 */
public record OrderDto(Long id, long productId, long quantity, Instant orderDate,
                       OrderStatus orderStatus, long amount) {
}
