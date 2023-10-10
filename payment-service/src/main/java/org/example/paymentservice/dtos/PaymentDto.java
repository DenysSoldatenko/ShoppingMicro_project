package org.example.paymentservice.dtos;

import org.example.paymentservice.entities.PaymentMethod;

/**
 * Data Transfer Object (DTO) representing payment information.
 */
public record PaymentDto(Long orderId, PaymentMethod paymentMethod, Long amount) {
}
