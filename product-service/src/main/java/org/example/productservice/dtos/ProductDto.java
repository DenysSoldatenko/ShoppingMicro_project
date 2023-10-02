package org.example.productservice.dtos;

/**
 * Data Transfer Object (DTO) representing product information.
 */
public record ProductDto(long id, String productName, long quantity, long price) {
}
