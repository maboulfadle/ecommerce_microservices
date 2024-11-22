package com.training.users.repositories;

import com.training.users.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The {@link UserRepository} interface contains {@link User} related database operations.
 *
 * @author mohammed
 * @since 2024.08
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Find user.
     *
     * @param uid the user uid
     * @return {@link User}
     */
    Optional<User> findUserByUid(final String uid);
}
