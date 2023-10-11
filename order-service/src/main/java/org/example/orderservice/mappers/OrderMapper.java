package org.example.orderservice.mappers;

import org.example.orderservice.dtos.OrderDto;
import org.example.orderservice.entities.Order;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between Order entities and DTOs.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderDto toDto(Order order);

  Order toModel(OrderDto orderDto);
}
