package org.example.productservice.initializers;

import static java.util.stream.IntStream.range;
import static org.example.productservice.utils.MessageConstants.DATA_INITIALIZATION_FAIL_MESSAGE;
import static org.example.productservice.utils.MessageConstants.DATA_INITIALIZATION_SUCCESS_MESSAGE;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.example.productservice.entities.Product;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuration class for initializing product data in the database.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProductDataInitializer {

  private final Faker faker = new Faker();
  private final ProductRepository productRepository;

  /**
   * Initializes product data in the database.
   */
  @Transactional
  public String initData() {
    int batchSize = 20;
    int totalProducts = 100;
    AtomicBoolean hasErrors = new AtomicBoolean(false);

    log.info("Starting product data initialization...");

    range(0, totalProducts / batchSize)
        .parallel()
        .forEach(batchIndex -> {
          try {
            List<Product> productBatch = generateProductBatch(batchSize);
            productRepository.saveAll(productBatch);
            log.info("Product batch {} inserted successfully.", batchIndex);
          } catch (Exception e) {
            log.error("Error in product batch {}: {}", batchIndex, e.getMessage(), e);
            hasErrors.set(true);
          }
        });

    if (hasErrors.get()) {
      log.warn("Product data initialization completed with errors.");
      return DATA_INITIALIZATION_FAIL_MESSAGE;
    } else {
      log.info("Product data initialization completed successfully.");
      return DATA_INITIALIZATION_SUCCESS_MESSAGE;
    }
  }

  private List<Product> generateProductBatch(int batchSize) {
    return IntStream.range(0, batchSize)
      .mapToObj(i -> new Product(
        faker.commerce().productName(),
        faker.number().numberBetween(1, 500),
        faker.number().numberBetween(100, 1000)
      ))
      .toList();
  }
}
