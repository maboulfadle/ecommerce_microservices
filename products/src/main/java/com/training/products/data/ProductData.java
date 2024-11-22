package com.training.products.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * The {@link ProductData} class contains product data information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
@Schema(name = "Product", description = "Schema that holds the product information")
public class ProductData {

    @Schema(description = "The code of the product", example = "L70996219")
    @NotEmpty(message = "Product code can not be a null or empty")
    @Pattern(regexp = "(^[A-Za-z0-9]{1,10})", message = "Product code should contains at most 10 alpha numeric characters")
    private String code;

    @Schema(description = "The name of the product", example = "Lit cabane 90x190")
    @NotEmpty(message = "Product name can not be a null or empty")
    @Size(min = 5, max = 60, message = "The length of the product name should be between 5 and 60")
    private String name;

    @Schema(description = "The price of the product", example = "580.21")
    @Positive(message = "The product's price must be positive")
    private double price;

    @Schema(description = "The description of the product", example = "Lit cabane 90x190 en bois sans matelas atmosphera")
    @NotEmpty(message = "Product description can not be a null or empty")
    private String description;
}
