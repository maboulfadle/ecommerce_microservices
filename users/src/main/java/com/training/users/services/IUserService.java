package com.training.users.services;

import com.training.users.data.AddressDTO;
import com.training.users.data.UserDTO;

/**
 * The {@link IUserService} interface contains {@link com.training.users.models.User} related operations.
 *
 * @author mohammed
 * @since 2024.08
 */
public interface IUserService {

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    UserDTO getUser(final String userId);

    /**
     * Register user.
     *
     * @param user the user data information
     */
    void register(final UserDTO user);

    /**
     * Create address.
     *
     * @param userId  the user id
     * @param address the address
     */
    void createAddress(final String userId, final AddressDTO address);
}
