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

  public static final String DATA_INITIALIZATION_SUCCESS_MESSAGE
      = "Data initialization completed successfully!";
  public static final String DATA_INITIALIZATION_FAIL_MESSAGE
      = "Data initialization completed with errors!";
}
