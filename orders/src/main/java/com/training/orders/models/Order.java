package com.training.orders.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The {@link Order} class contains order mongo document information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String code;

    private OrderStatus status;

    private List<OrderItem> items;
    private Double totalPrice;

    private PaymentMode paymentMode;
    private LocalDateTime orderDate;
}
