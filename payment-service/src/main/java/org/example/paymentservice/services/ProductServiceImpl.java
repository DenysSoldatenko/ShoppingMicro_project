package org.example.paymentservice.services;

import static org.example.paymentservice.utils.MessageConstants.PAYMENT_DETAILS_NOT_FOUND;
import static org.example.paymentservice.utils.PaymentTransactionFactory.createPaymentTransaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentservice.dtos.PaymentDto;
import org.example.paymentservice.entities.PaymentTransaction;
import org.example.paymentservice.mappers.PaymentMapper;
import org.example.paymentservice.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

/**
 * Service implementation for managing payments.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper paymentMapper;

  @Override
  public PaymentDto processPayment(PaymentDto paymentDto) {
    log.info("Recording Payment Details: {}", paymentDto);

    PaymentTransaction paymentTransaction = createPaymentTransaction(paymentDto);
    paymentRepository.save(paymentTransaction);

    log.info("Payment details recorded successfully. Transaction Id: {}",
        paymentTransaction.getId());

    return paymentMapper.toDto(paymentTransaction);
  }

  @Override
  public PaymentDto getPaymentDetailsByOrderId(long orderId) {
    log.info("Retrieving payment details for the Order Id: {}", orderId);

    PaymentTransaction paymentTransaction = paymentRepository.findByOrderId(orderId)
        .orElseThrow(() -> new RuntimeException(PAYMENT_DETAILS_NOT_FOUND + orderId));

    log.info("Payment details retrieved successfully for Order Id: {}. Transaction Id: {}",
        orderId, paymentTransaction.getId());

    return paymentMapper.toDto(paymentTransaction);
  }
}
