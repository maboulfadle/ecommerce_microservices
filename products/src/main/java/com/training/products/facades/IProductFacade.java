package com.training.products.facades;

import com.training.products.data.ProductData;

/**
 * The {@link IProductFacade} interface contains {@link ProductData} related operations.
 *
 * @author mohammed
 * @since 2024.08
 */
public interface IProductFacade {

    /**
     * Gets product details for the given {@code productCode}.
     *
     * @param productCode the product code
     * @return the product
     */
    ProductData getProduct(final String productCode);

    /**
     * Create product.
     *
     * @param product the product
     */
    void createProduct(final ProductData product);

    /**
     * Delete product for the given {@code productCode}.
     *
     * @param productCode the product code
     * @return {@link Boolean#TRUE} if product get deleted, {@link Boolean#FALSE} otherwise
     */
    boolean deleteProduct(final String productCode);
}
