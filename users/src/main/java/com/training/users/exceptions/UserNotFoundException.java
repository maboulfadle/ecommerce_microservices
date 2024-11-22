package com.training.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * The {@link UserNotFoundException} class defines the exception triggered when the user is not found.
 *
 * @author mohammed
 * @since 2024.08
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Instantiates a new User not found exception.
     *
     * @param message the message
     */
    public UserNotFoundException(final String message) {
        super(message);
    }
}
