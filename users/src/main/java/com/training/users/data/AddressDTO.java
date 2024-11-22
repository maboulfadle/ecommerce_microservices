package com.training.users.data;

import jakarta.validation.constraints.NotEmpty;

/**
 * The {@link AddressDTO} record contains address data information.
 *
 * @author mohammed
 * @since 2024.08
 */
public record AddressDTO(@NotEmpty(message = "Id should not be empty") String id,
                         @NotEmpty(message = "line1 should not be empty") String line1,
                         String line2,
                         @NotEmpty(message = "town should not be empty") String town,
                         @NotEmpty(message = "country should not be empty") String country,
                         @NotEmpty(message = "postal code should not be empty") String postalCode) {}
