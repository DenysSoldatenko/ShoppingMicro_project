package org.example.productservice.services;

import static org.example.productservice.utils.MessageConstants.INSUFFICIENT_QUANTITY;
import static org.example.productservice.utils.MessageConstants.PRODUCT_NOT_FOUND;
import static org.example.productservice.utils.ProductFactory.createProduct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.productservice.dtos.ProductDto;
import org.example.productservice.entities.Product;
import org.example.productservice.exceptions.ProductServiceException;
import org.example.productservice.mappers.ProductMapper;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing products.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;

  @Override
  public ProductDto addProduct(ProductDto productRequest) {
    log.info("Adding product: {}", productRequest);

    Product product = createProduct(productRequest);
    productRepository.save(product);

    log.info("Product added successfully: {}", product);
    return productMapper.toDto(product);
  }

  @Override
  public ProductDto getProductById(long productId) {
    log.info("Fetching product for id: {}", productId);

    Product product = productRepository.findById(productId)
        .orElseThrow(() -> new ProductServiceException(PRODUCT_NOT_FOUND + productId));

    log.info("Product fetched successfully: {}", product);
    return productMapper.toDto(product);
  }

  @Override
  public ProductDto reduceProductQuantity(long productId, long quantity) {
    log.info("Reducing quantity {} for id: {}", quantity, productId);

    Product product = productRepository.findById(productId)
        .filter(p -> p.getQuantity() >= quantity)
        .orElseThrow(() -> new ProductServiceException(
          (productRepository.existsById(productId)
            ? INSUFFICIENT_QUANTITY
            : PRODUCT_NOT_FOUND)
            + productId)
        );

    product.setQuantity(product.getQuantity() - quantity);
    productRepository.save(product);

    log.info("Quantity reduced successfully. Updated product: {}", product);
    return productMapper.toDto(product);
  }
}
