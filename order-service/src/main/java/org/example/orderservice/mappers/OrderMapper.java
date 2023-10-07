package org.example.orderservice.mappers;

import org.example.orderservice.dtos.CustomerOrderDto;
import org.example.orderservice.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

  @Mappings({
    @Mapping(target = "productId", source = "order.productId"),
    @Mapping(target = "amount", source = "order.amount"),
    @Mapping(target = "quantity", source = "order.quantity")
  })
  CustomerOrderDto toDto(Order order);

  Order toModel(CustomerOrderDto orderDto);
}