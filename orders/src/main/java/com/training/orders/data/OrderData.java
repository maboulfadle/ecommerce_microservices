package com.training.orders.data;

import com.training.orders.models.OrderStatus;
import com.training.orders.models.PaymentMode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * The {@link OrderData} class contains order data information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
public class OrderData {

    @Schema(description = "The order code", example = "123456789")
    private String code;

    @Schema(description = "The order status")
    private OrderStatus status;

    @Schema(description = "The order payment mode")
    private PaymentMode paymentMode;

    @Schema(description = "The order total price", example = "999.99")
    private Double totalPrice;

    @Schema(description = "The order items")
    private List<OrderItemData> items;
}
