package org.example.orderservice.entities;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private long productId;

  private long quantity;

  private Instant orderDate;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  private long amount;
}
