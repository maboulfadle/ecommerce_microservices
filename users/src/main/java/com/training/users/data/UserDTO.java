package com.training.users.data;

import com.training.users.validators.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * The {@link UserDTO} record contains user data information.
 *
 * @author mohammed
 * @since 2024.08
 */
public record UserDTO(String uid,
                      @NotEmpty(message = "name cannot be empty") String name,
                      @Email(message = "email cannot be invalid") String email,
                      @Password(message = "password cannot be invalid") String password,
                      List<AddressDTO> addresses) {


}
