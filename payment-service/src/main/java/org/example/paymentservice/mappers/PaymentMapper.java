package org.example.paymentservice.mappers;

import org.example.paymentservice.dtos.PaymentDto;
import org.example.paymentservice.entities.PaymentTransaction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface
 * for converting between {@link PaymentTransaction} entities and {@link PaymentDto} DTOs.
 */
@Component
@Mapper(componentModel = "spring")
public interface PaymentMapper {

  PaymentTransaction toModel(PaymentDto dto);

  PaymentDto toDto(PaymentTransaction entity);
}
