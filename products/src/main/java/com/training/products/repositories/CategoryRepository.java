package com.training.products.repositories;

import com.training.products.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link CategoryRepository} interface contains {@link Category} related database operations.
 *
 * @author mohammed
 * @since 2024.08
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    /**
     * Gets category for the given {@code code}.
     *
     * @param code the code
     * @return the {@link Category}
     */
    Category findCategoryByCode(final String code);
}
