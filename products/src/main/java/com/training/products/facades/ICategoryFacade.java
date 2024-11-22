package com.training.products.facades;

import com.training.products.data.CategoryData;

/**
 * The {@link ICategoryFacade} interface contains {@link CategoryData} related operations.
 *
 * @author mohammed
 * @since 2024.08
 */
public interface ICategoryFacade {

    /**
     * Gets category details for the given {@code categoryCode}.
     *
     * @param categoryCode the category code
     *
     * @return the {@link CategoryData}
     */
    CategoryData getCategory(final String categoryCode);

    /**
     * Create category.
     *
     * @param category the {@link CategoryData}
     */
    void createCategory(final CategoryData category);

    /**
     * Add the product for the given {@code productCode} to the category for the given {@code categoryCode}.
     *
     * @param categoryCode the category code
     * @param productCode  the product code
     *
     * @return {@link Boolean#TRUE} if product get add the given category, {@link Boolean#FALSE} otherwise
     */
    boolean addProduct(final String categoryCode, final String productCode);
}
