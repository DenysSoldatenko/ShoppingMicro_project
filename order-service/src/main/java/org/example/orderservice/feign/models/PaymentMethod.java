package org.example.orderservice.feign.models;

/**
 * Enum representing various payment methods.
 */
public enum PaymentMethod {
  CASH, PAYPAL, DEBIT_CARD,
  CREDIT_CARD, APPLE_PAY, GOOGLE_PAY,
  AMAZON_PAY, BANK_TRANSFER
}