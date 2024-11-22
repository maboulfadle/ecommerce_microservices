package com.training.orders.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The {@link OrderItem} entity class contains order item model information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Document(collection = "orderItems")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    private Long id;

    @Indexed(unique = true)
    private String productCode;

    private Long quantity;
    private Double totalPrice;
}
