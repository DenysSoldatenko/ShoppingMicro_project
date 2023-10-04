package org.example.orderservice.dtos;

import java.time.Instant;

public record AdminOrderDto(long orderId, Instant orderDate, String orderStatus, long amount) {
}
