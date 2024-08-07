package org.example.productservice.controllers;

import static org.example.productservice.utils.MessageConstants.DATA_INITIALIZATION_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.CREATED;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.productservice.dtos.ProductDto;
import org.example.productservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for managing product-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Product Controller", description = "APIs for managing products")
public class ProductController {

  private final ProductService productService;

  @Operation(summary = "Initialize product data")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "201", description = "Data initialized successfully",
      content = {
        @Content(mediaType = "application/text",
          schema = @Schema(type = "string", example = DATA_INITIALIZATION_SUCCESS_MESSAGE))
      }),
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
  })
  @PostMapping("/initialize")
  public ResponseEntity<String> initializeData() {
    return new ResponseEntity<>(productService.initializeData(), CREATED);
  }

  @PostMapping
  @Operation(
      summary = "Add a new product",
      description = "Create a new product with the given details"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "201", description = "Product created successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid input data"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productRequest) {
    ProductDto product = productService.createProduct(productRequest);
    return new ResponseEntity<>(product, CREATED);
  }

  @GetMapping("/{id}")
  @Operation(
      summary = "Get product by ID",
      description = "Retrieve a product by its unique identifier"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
    @ApiResponse(responseCode = "404", description = "Product not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ProductDto getProductById(@PathVariable("id") long productId) {
    return productService.getProductById(productId);
  }

  @PutMapping("/{id}/reduceQuantity")
  @Operation(
      summary = "Reduce product quantity",
      description = "Reduce the quantity of a specific product by the specified amount"
  )
  @ApiResponses({
    @ApiResponse(responseCode = "200", description = "Product quantity reduced successfully"),
    @ApiResponse(responseCode = "400", description = "Invalid quantity specified"),
    @ApiResponse(responseCode = "404", description = "Product not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
  })
  public ProductDto reduceQuantity(
      @PathVariable("id") long productId, @RequestParam(name = "quantity") int quantity
  ) {
    return productService.reduceProductQuantity(productId, quantity);
  }
}
