package com.training.products.services.impl;

import com.training.products.models.Category;
import com.training.products.models.Product;
import com.training.products.repositories.CategoryRepository;
import com.training.products.services.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@link CategoryService} class is a default implementation of {@link ICategoryService} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category getCategory(final String categoryCode) {
        return categoryRepository.findCategoryByCode(categoryCode);
    }

    @Override
    public void createCategory(final Category category) {
        final String categoryCode = category.getCode();
        final Category loadedCategory = categoryRepository.findCategoryByCode(categoryCode);

        if (loadedCategory != null) {
            throw new DuplicateKeyException(String.format("Category found with the same code %s", category));
        }

        categoryRepository.save(category);
    }

    @Override
    public boolean addProduct(final Category category, final Product product) {
        List<Product> products = category.getProducts();

        if (CollectionUtils.isEmpty(products)) {
            products = new ArrayList<>();
        }
        products.add(product);
        category.setProducts(products);

        categoryRepository.save(category);
        return true;
    }
}
