package org.example.productservice.utils;

import lombok.experimental.UtilityClass;

/**
 * Class containing constants for messages used in the ProductService.
 */
@UtilityClass
public class MessageConstants {

  public static final String PRODUCT_NOT_FOUND = "Product not found with id: ";
  public static final String INSUFFICIENT_QUANTITY = "Product does not have sufficient quantity "
      + "available for id: ";
}
