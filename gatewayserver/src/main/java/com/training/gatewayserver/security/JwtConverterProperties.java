package com.training.gatewayserver.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * The {@link JwtConverterProperties} class contains jwt converter mapping properties.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
@Validated
@Configuration
@ConfigurationProperties(prefix = "jwt.auth2.converter")
public class JwtConverterProperties {

    private String resourceId;
    private String principalAttribute;
}