package com.training.users.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * The {@link User} class contains user mongo document information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String uid;

    private String email;

    private String name;
    private String password;

    private List<Address> addresses;
}
