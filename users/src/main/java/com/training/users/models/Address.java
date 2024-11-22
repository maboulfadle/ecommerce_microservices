package com.training.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The {@link Address} class contains user mongo document information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Document(collection = "addresses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    private String id;

    private String line1;
    private String line2;

    private String town;
    private String country;

    private String postalCode;
}
