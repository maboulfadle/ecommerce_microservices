package com.training.orders.data;

/**
 * The {@link ProductData} record contains product data information.
 *
 * @author mohammed
 * @since 2024.08
 */
public record ProductData (String code,
                           String name,
                           double price,
                           String description) {}