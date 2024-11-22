package com.training.orders.services;

import com.training.orders.data.OrderData;
import com.training.orders.data.OrderItemData;

/**
 * The {@link IOrderService} interface contains {@link com.training.orders.models.Order} related operations.
 *
 * @author mohammed
 * @since 2024.08
 */
public interface IOrderService {

    /**
     * Initialize order.
     *
     * @return the order
     */
    OrderData initializeOrder();

    /**
     * Gets order.
     *
     * @param orderCode the order code
     * @return the order
     */
    OrderData getOrder(final String orderCode);

    /**
     * Add order entry.
     *
     * @param orderCode   the order code
     * @param productCode the product code
     * @param quantity    the quantity
     * @return the order item
     */
    OrderItemData addOrderEntry(final String orderCode, final String productCode, final Long quantity);

    /**
     * Place order.
     *
     * @param orderCode the order code
     * @return the order data
     */
    OrderData placeOrder(final String orderCode);
}