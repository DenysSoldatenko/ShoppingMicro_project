package org.example.productservice.mappers;

import org.example.productservice.dtos.ProductDto;
import org.example.productservice.entities.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

/**
 * Mapper interface
 * for converting between {@link Product} entities and {@link ProductDto} DTOs.
 */
@Component
@Mapper(componentModel = "spring")
public interface ProductMapper {

  Product toModel(ProductDto postDto);

  ProductDto toDto(Product post);
}