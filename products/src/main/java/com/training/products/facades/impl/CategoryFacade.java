package com.training.products.facades.impl;

import com.training.products.data.CategoryData;
import com.training.products.facades.ICategoryFacade;
import com.training.products.models.Category;
import com.training.products.models.Product;
import com.training.products.services.ICategoryService;
import com.training.products.services.IProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * The {@link CategoryFacade} class is a default implementation of {@link ICategoryFacade} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Component
@RequiredArgsConstructor
public class CategoryFacade implements ICategoryFacade {

    private final ICategoryService categoryService;
    private final IProductService productService;
    private ModelMapper mapper;

    /**
     * will be executed after bean initialization.
     */
    @PostConstruct
    public void init() {
        mapper = new ModelMapper();
    }

    @Override
    public CategoryData getCategory(final String categoryCode) {
        final Category category = categoryService.getCategory(categoryCode);

        return mapper.map(category, CategoryData.class);
    }

    @Override
    public void createCategory(final CategoryData categoryDTO) {
        final Category category = mapper.map(categoryDTO, Category.class);

        categoryService.createCategory(category);
    }

    @Override
    public boolean addProduct(final String categoryCode, final String productCode) {
        final Category category = categoryService.getCategory(categoryCode);
        final Product product = productService.getProduct(productCode);

        return categoryService.addProduct(category, product);
    }
}
