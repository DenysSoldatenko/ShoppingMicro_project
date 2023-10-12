package org.example.orderservice.dtos;


import org.example.orderservice.feign.models.PaymentMethod;

/**
 * Represents a data transfer object (DTO) for processing order requests.
 */
public record RequestDto(long productId, long amount,
                         long quantity, PaymentMethod paymentMethod) {
}
