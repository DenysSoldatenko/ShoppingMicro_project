package org.example.productservice.services;

import org.example.productservice.dtos.ProductDto;

/**
 * Service interface for managing products.
 */
public interface ProductService {

  ProductDto addProduct(ProductDto productDto);

  ProductDto getProductById(long productId);

  ProductDto reduceProductQuantity(long productId, long quantity);
}
