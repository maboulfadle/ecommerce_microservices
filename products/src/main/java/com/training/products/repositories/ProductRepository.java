package com.training.products.repositories;

import com.training.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The {@link ProductRepository} interface contains {@link Product} related database operations.
 *
 * @author mohammed
 * @since 2024.08
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Gets product.
     *
     * @param code the code
     * @return the product
     */
    @Query("SELECT p FROM Product AS p WHERE p.code = ?1")
    Product getProduct(final String code);
}
