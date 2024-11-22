package com.training.products.services.impl;

import com.training.products.exceptions.ProductNotFoundException;
import com.training.products.models.Product;
import com.training.products.repositories.ProductRepository;
import com.training.products.services.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

/**
 * The {@link ProductService} class is a default implementation of {@link IProductService} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public Product getProduct(final String productCode) {
        return productRepository.getProduct(productCode);
    }

    @Override
    public void createProduct(final Product product) {
        final String productCode = product.getCode();
        final Product loadedProduct = productRepository.getProduct(productCode);

        if (loadedProduct != null) {
            throw new DuplicateKeyException(String.format("Product found with the same code %s", productCode));
        }

        productRepository.save(product);
    }

    @Override
    @Transactional
    @Modifying
    public boolean deleteProduct(final String productCode) {
        final Product product = productRepository.getProduct(productCode);

        if (product == null) {
            throw new ProductNotFoundException(String.format("no product found for code %s", productCode));
        }

        productRepository.delete(product);
        return true;
    }
}
