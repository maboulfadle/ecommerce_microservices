package com.training.orders.repositories;

import com.training.orders.models.Order;
import com.training.orders.models.OrderItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The {@link OrderItemRepository} interface contains {@link OrderItem} related database operations.
 *
 * @author mohammed
 * @since 2024.08
 */
@Repository
public interface OrderItemRepository extends MongoRepository<OrderItem, String> {

    /**
     * Find order by code.
     *
     * @param productCode the product code
     * @return {@link Order}
     */
    Optional<OrderItem> findOrderItemByProductCode(final String productCode);
}
