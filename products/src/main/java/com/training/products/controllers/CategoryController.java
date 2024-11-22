package com.training.products.controllers;

import com.training.products.data.CategoryData;
import com.training.products.data.ErrorResponse;
import com.training.products.data.Response;
import com.training.products.facades.ICategoryFacade;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The {@link CategoryController} class exposes the RESTFULL endpoints to get category information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Tag(name = "Category REST APIs", description = "REST APIs to create, update, get and delete category details")
@RestController
@RequestMapping(value = "/api/c/", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryFacade categoryFacade;

    /**
     * Gets the category for the given {@code categoryCode}.
     *
     * @return the {@link CategoryData}
     */
    @Operation(
        summary = "Fetch category details REST API",
        description = "REST API to fetch category based on the category code"
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
    @GetMapping("/{categoryCode}")
    public CategoryData getCategory(@PathVariable final String categoryCode) {
        return categoryFacade.getCategory(categoryCode);
    }

    /**
     * Creates a category.
     *
     * @param category the {@link CategoryData}
     */
    @Operation(
        summary = "Create category REST API",
        description = "REST API to create new category"
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
    public ResponseEntity<Response> createCategory(@Valid @RequestBody final CategoryData category) {
        categoryFacade.createCategory(category);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new Response(HttpStatus.CREATED.value(), "Category created successfully"));
    }

    /**
     * Add the given {@code productCode} to the current category.
     *
     * @param categoryCode the category code
     * @param productCode  the product code
     */
    @Operation(
        summary = "Add product to category REST API",
        description = "REST API to add product to category based on the given params"
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
    @PostMapping("/{categoryCode}/addProduct")
    public ResponseEntity<Response> addProduct(@PathVariable final String categoryCode, @RequestParam final String productCode) {
        final boolean added = categoryFacade.addProduct(categoryCode, productCode);

        if(added) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new Response(HttpStatus.OK.value(), String.format("Product %s added successfully to the category %s", productCode, categoryCode)));

        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new Response(HttpStatus.EXPECTATION_FAILED.value(), String.format("Error occurred while adding the product %s", productCode)));
        }
    }
}
