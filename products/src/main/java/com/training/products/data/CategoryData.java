package com.training.products.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * The {@link CategoryData} class contains product data information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
@Schema(name = "Category", description = "Schema that holds the category information")
public class CategoryData {

    @Schema(description = "The code of the category", example = "L70996219")
    @NotEmpty(message = "Category code can not be a null or empty")
    @Pattern(regexp = "(^[A-Za-z0-9]{1,10})", message = "Category code should contains at most 10 alpha numeric characters")
    private String code;

    @Schema(description = "The name of the category", example = "Lit cabane 90x190")
    @NotEmpty(message = "Category name can not be a null or empty")
    @Size(min = 5, max = 100, message = "The length of the category name should be between 5 and 100")
    private String name;

    @Schema(description = "The product belong to the category")
    private List<ProductData> products;
}
