package com.training.users.repositories;

import com.training.users.models.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The {@link AddressRepository} interface contains {@link Address} related database operations.
 *
 * @author mohammed
 * @since 2024.08
 */
@Repository
public interface AddressRepository extends MongoRepository<Address, String> { }
