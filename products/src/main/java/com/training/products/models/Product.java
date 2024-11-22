package com.training.products.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The {@link Product} entity class contains product model information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Entity
@Table(name = "products")
@Getter
@Setter
@ToString
// avoid @Data annotation because sometimes it generates hashcode, and it can have impact on spring data jpa framework
@RequiredArgsConstructor
public class Product {

    @Id
    private String code;
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;

    @Transient
    private String calculatedLabel; // Transient used on dynamic field (not persisted but calculated)
}
