package com.training.orders.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The {@link ContactProperties} class represents the contact configuration properties.
 *
 * @author mohammed
 * @since 2024.08
 */
@ConfigurationProperties(prefix = "contact")
public record ContactProperties(String name, String email) {}
