package com.training.products.services;

import com.training.products.models.Product;

/**
 * The {@link IProductService} interface contains {@link Product} related operations.
 *
 * @author mohammed
 * @since 2024.08
 */
public interface IProductService {

    /**
     * Gets product for the given {@code code}.
     *
     * @param code the product code
     * @return the {@link Product}
     */
    Product getProduct(final String code);

    /**
     * Create product.
     *
     * @param product the product
     */
    void createProduct(final Product product);

    /**
     * Delete product.
     *
     * @param productCode the product code
     */
    boolean deleteProduct(final String productCode);
}
