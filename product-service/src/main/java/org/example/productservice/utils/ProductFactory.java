package org.example.productservice.utils;

import lombok.experimental.UtilityClass;
import org.example.productservice.dtos.ProductDto;
import org.example.productservice.entities.Product;

/**
 * Utility class for creating Product entities from ProductDto objects.
 */
@UtilityClass
public class ProductFactory {

  /**
   * Creates a Product entity from a ProductDto.
   *
   * @param productRequest The ProductDto object.
   * @return A Product entity created from the ProductDto.
   */
  public static Product createProduct(ProductDto productRequest) {
    return Product.builder()
      .productName(productRequest.productName())
      .quantity(productRequest.quantity())
      .price(productRequest.price())
      .build();
  }
}
