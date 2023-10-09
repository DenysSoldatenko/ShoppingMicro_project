package org.example.paymentservice.repositories;

import java.util.Optional;
import org.example.paymentservice.entities.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing Payment entities.
 */
@Repository
public interface PaymentRepository extends JpaRepository<PaymentTransaction, Long> {
  Optional<PaymentTransaction> findByOrderId(long orderId);
}
