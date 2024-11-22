package com.training.orders.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * The {@link OrderItemData} class contains order item data information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
public class OrderItemData {

    @Schema(description = "The order item product")
    private ProductData product;

    @Schema(description = "The order item quantity", example = "2")
    private Long quantity;

    @Schema(description = "The order item total price", example = "499.99")
    private Double totalPrice;
}
