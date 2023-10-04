package org.example.orderservice.dtos;

public record CustomerOrderDto(long productId, long amount, long quantity) {
}
