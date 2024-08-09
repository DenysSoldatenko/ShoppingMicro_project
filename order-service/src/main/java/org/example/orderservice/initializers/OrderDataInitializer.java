package org.example.orderservice.initializers;

import static java.util.stream.IntStream.range;
import static org.example.orderservice.utils.MessageConstants.DATA_INITIALIZATION_FAIL_MESSAGE;
import static org.example.orderservice.utils.MessageConstants.DATA_INITIALIZATION_SUCCESS_MESSAGE;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.example.orderservice.dtos.RequestDto;
import org.example.orderservice.feign.models.PaymentMethod;
import org.example.orderservice.services.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuration class for initializing order data in the database.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OrderDataInitializer {

  private final Faker faker = new Faker();
  private final OrderService orderService;

  /**
   * Initializes order data in the database.
   */
  @Transactional
  public String initData() {
    int batchSize = 5;
    int totalOrders = 25;
    AtomicBoolean hasErrors = new AtomicBoolean(false);

    log.info("Starting order data initialization...");

    range(0, totalOrders / batchSize)
        .parallel()
        .forEach(batchIndex -> {
          try {
            List<RequestDto> orderBatch = generateOrderBatch(batchSize);
            orderBatch.forEach(requestDto -> {
              try {
                orderService.addOrder(requestDto);
                log.info("Order with product ID {} inserted successfully.", requestDto.productId());
              } catch (Exception e) {
                log.error("Error inserting order with product ID {}: {}", requestDto.productId(), e.getMessage(), e);
                hasErrors.set(true);
              }
            });
          } catch (Exception e) {
            log.error("Error generating order batch {}: {}", batchIndex, e.getMessage(), e);
            hasErrors.set(true);
          }
        });

    if (hasErrors.get()) {
      log.warn("Order data initialization completed with errors.");
      return DATA_INITIALIZATION_FAIL_MESSAGE;
    } else {
      log.info("Order data initialization completed successfully.");
      return DATA_INITIALIZATION_SUCCESS_MESSAGE;
    }
  }

  private List<RequestDto> generateOrderBatch(int batchSize) {
    return IntStream.range(0, batchSize)
      .mapToObj(i -> new RequestDto(
        faker.number().numberBetween(1, 100), // productId
        faker.number().numberBetween(10, 100), // amount
        faker.number().numberBetween(1, 10), // quantity
        faker.options().option(PaymentMethod.class) // paymentMethod
      ))
      .toList();
  }
}
