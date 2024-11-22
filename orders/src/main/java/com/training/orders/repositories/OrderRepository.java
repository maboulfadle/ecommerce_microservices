package com.training.orders.repositories;

import com.training.orders.models.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link OrderRepository} interface contains {@link Order} related database operations.
 *
 * @author mohammed
 * @since 2024.08
 */
@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    /**
     * Find order by code.
     *
     * @param code the order code
     * @return {@link Order}
     */
    // @Query("{'totalPrice' : { $gt : ?0 } }")
    Order findOrderByCode(final String code);
}
