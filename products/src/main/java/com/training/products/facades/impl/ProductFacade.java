package com.training.products.facades.impl;

import com.training.products.data.ProductData;
import com.training.products.exceptions.ProductNotFoundException;
import com.training.products.facades.IProductFacade;
import com.training.products.models.Product;
import com.training.products.services.IProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * The {@link ProductFacade} class is a default implementation of {@link IProductFacade} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Component
@RequiredArgsConstructor
public class ProductFacade implements IProductFacade {

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
    public ProductData getProduct(final String productCode) {
        final Product product = productService.getProduct(productCode);

        if (product == null) {
            throw new ProductNotFoundException(String.format("no product found for code %s", productCode));
        }

        return mapper.map(product, ProductData.class);
    }

    @Override
    public void createProduct(final ProductData productDTO) {
        final Product product = mapper.map(productDTO, Product.class);

        productService.createProduct(product);
    }

    @Override
    public boolean deleteProduct(final String productCode) {
        return productService.deleteProduct(productCode);
    }
}
