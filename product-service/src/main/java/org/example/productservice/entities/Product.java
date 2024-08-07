package org.example.productservice.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a product in the system.
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "products")
@JsonNaming(SnakeCaseStrategy.class)
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String productName;

  private long price;

  private long quantity;

  /**
   * Constructs a new {@code Product} with the specified name, price, and quantity.
   *
   * @param productName the name of the product
   * @param price the price of the product
   * @param quantity the quantity of the product in stock
   */
  public Product(String productName, long price, long quantity) {
    this.productName = productName;
    this.price = price;
    this.quantity = quantity;
  }
}
