package org.example.paymentservice.dtos;

import java.time.Instant;
import org.example.paymentservice.entities.PaymentMethod;

/**
 * Data Transfer Object (DTO) representing payment information.
 */
public record PaymentDto(Long id, Long orderId, PaymentMethod paymentMethod,
                         String referenceNumber, Instant paymentDate,
                         String paymentStatus, Long amount) {
}
