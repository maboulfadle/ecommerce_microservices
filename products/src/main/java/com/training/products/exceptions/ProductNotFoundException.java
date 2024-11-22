package com.training.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link ProductNotFoundException} class defines the exception triggered when the product is not found.
 *
 * @author mohammed
 * @since 2024.08
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {

    /**
     * Instantiates a new Product un found exception.
     *
     * @param message the message
     */
    public ProductNotFoundException(final String message) {
        super(message);
    }
}
