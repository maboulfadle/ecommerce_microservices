package com.training.users.services.impl;

import com.training.users.data.AddressDTO;
import com.training.users.data.UserDTO;
import com.training.users.exceptions.UserNotFoundException;
import com.training.users.models.Address;
import com.training.users.models.User;
import com.training.users.repositories.AddressRepository;
import com.training.users.repositories.UserRepository;
import com.training.users.services.IUserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * The {@link UserService} class is a default implementation of {@link IUserService} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private ModelMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new ModelMapper();
    }

    @Override
    public UserDTO getUser(final String id) {
        final User user = userRepository.findUserByUid(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for the given id %s", id)));

        return mapper.map(user, UserDTO.class);
    }

    @Override
    public void register(final UserDTO user) {
        final User userEntity = User.builder()
                .uid(UUID.randomUUID().toString())
                .email(user.email())
                .name(user.name())
                .password(Base64.getEncoder().encodeToString(user.password().getBytes()))
                .build();

        userRepository.save(userEntity);
    }

    @Override
    @Transactional
    public void createAddress(final String userId, final AddressDTO address) {
        final User user = userRepository.findUserByUid(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for the given id %s", userId)));

        final Address addressEntity = Address.builder()
                .id(String.join("-", userId, address.id()))
                .line1(address.line1())
                .line2(address.line2())
                .town(address.town())
                .postalCode(address.postalCode())
                .build();

        addressRepository.save(addressEntity);

        user.setAddresses(List.of(addressEntity));
        userRepository.save(user);
    }
}
