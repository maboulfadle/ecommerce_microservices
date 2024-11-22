package com.training.products.services;

import com.training.products.models.Category;
import com.training.products.models.Product;

/**
 * The {@link ICategoryService} interface contains {@link Category} related operations.
 *
 * @author mohammed
 * @since 2024.08
 */
public interface ICategoryService {

    /**
     * Gets category for the given {@code code}.
     *
     * @param code the category code
     * @return the {@link Category}
     */
    Category getCategory(final String code);

    /**
     * Create category.
     *
     * @param category the {@link Category}
     */
    void createCategory(final Category category);

    /**
     * Add the given {@code product} to the given {@code category}.
     *
     * @param category the {@link Category}
     * @param product  the {@link Product}
     *
     * @return {@link Boolean#TRUE} if product get add the given category, {@link Boolean#FALSE} otherwise
     */
    boolean addProduct(final Category category, final Product product);
}
