package com.training.gatewayserver.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@link RoleConverter} class converts the jwt ressources roles into granted authorities.
 *
 * @author mohammed
 * @since 2024.08
 */
@SuppressWarnings("ALL")
@Component
public class RoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    private final JwtConverterProperties properties;

    public RoleConverter(final JwtConverterProperties properties) {
        this.properties = properties;
    }

    @Override
    public Collection<GrantedAuthority> convert(final Jwt jwt) {
        final Map<String, Object> access = jwt.getClaim("resource_access");
        final Map<String, Object> resource;
        final Collection<String> resourceRoles;

        if (access == null
                || (resource = (Map<String, Object>) access.get(properties.getResourceId())) == null
                || (resourceRoles = (Collection<String>) resource.get("roles")) == null) {

            return Set.of();
        }

        return resourceRoles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
