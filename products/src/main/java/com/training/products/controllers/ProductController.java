package com.training.products.controllers;

import com.training.products.data.ErrorResponse;
import com.training.products.data.ProductData;
import com.training.products.data.Response;
import com.training.products.facades.IProductFacade;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The {@link ProductController} class exposes the RESTFULL endpoints to get product information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Slf4j
@Tag(name = "Products REST APIs", description = "REST APIs to create, update, get and delete product details")
@RestController
@RequestMapping(value = "/api/p/", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ProductController {

    private final IProductFacade productFacade;
    private final Environment environment;

    /**
     * Gets product.
     *
     * @param productCode the product code
     * @return the product
     */
    @Operation(
        summary = "Fetch product details REST API",
        description = "REST API to fetch product based on the product code"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @Retry(name = "getProduct")
    @GetMapping("/{productCode}")
    public ProductData getProduct(@PathVariable final String productCode) {
        return productFacade.getProduct(productCode);
    }


    /**
     * Create product response entity.
     *
     * @param product the product
     * @return the response entity
     */
    @Operation(
        summary = "Create product REST API",
        description = "REST API to create new product"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @PostMapping("/create")
    public ResponseEntity<Response> createProduct(final @Valid @RequestBody ProductData product) {
        productFacade.createProduct(product);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response(HttpStatus.CREATED.value(), "Product created successfully"));
    }


    /**
     * Create product response entity.
     *
     * @param productCode the product code
     * @return the response entity
     */
    @Operation(
        summary = "Delete product details REST API",
        description = "REST API to delete product based on the product code"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "417",
            description = "Expectation Failed"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @DeleteMapping("/{productCode}/delete")
    public ResponseEntity<Response> createProduct(final @PathVariable String productCode) {
        final boolean deleted = productFacade.deleteProduct(productCode);

        if(deleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response(HttpStatus.OK.value(), "Product deleted successfully"));

        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new Response(HttpStatus.EXPECTATION_FAILED.value(), "Error occurred while deleting the product"));
        }
    }


    /**
     * Gets build info.
     *
     * @return the build info
     */
    @Operation(
        summary = "Get build info REST API",
        description = "REST API to get products microservice build info"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        )
    })
    @RateLimiter(name = "buildInformationRateLimiter")
    @GetMapping("/buildInformation")
    public ResponseEntity<String> getBuildInformation() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(environment.getProperty("build.version"));
    }
}
