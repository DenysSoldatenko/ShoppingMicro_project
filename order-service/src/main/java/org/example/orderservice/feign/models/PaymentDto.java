package org.example.orderservice.feign.models;

/**
 * Data Transfer Object (DTO) representing payment information.
 */
public record PaymentDto(Long orderId, PaymentMethod paymentMethod, Long amount) {
}
