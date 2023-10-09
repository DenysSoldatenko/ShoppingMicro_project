package org.example.orderservice.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;
import org.example.orderservice.exceptions.ErrorDetails;
import org.example.orderservice.exceptions.OrderServiceException;
import java.io.IOException;

@Log4j2
public class CustomErrorDecoder implements ErrorDecoder {

  @Override
  public Exception decode(String methodKey, Response response) {
    log.info("Error decoding for method '{}'. Request URL: {}",
        methodKey, response.request().url());

    ObjectMapper objectMapper = new ObjectMapper();

    try {
      ErrorDetails errorResponse = objectMapper.readValue(
          response.body().asInputStream(), ErrorDetails.class);
      log.error("Error details - Message: '{}', Error Code: '{}', Status: '{}'",
          errorResponse.message(), errorResponse.error(), errorResponse.status());
      return new OrderServiceException(errorResponse.message(),
          errorResponse.error(), errorResponse.status());
    } catch (IOException e) {
      log.error("Failed to deserialize error response body. Original response: {}",
          response.body().toString());
      return new OrderServiceException("Error decoding response body", "UNKNOWN_ERROR", "500");
    }
  }
}