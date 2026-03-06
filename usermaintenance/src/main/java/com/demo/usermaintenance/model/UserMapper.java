package com.demo.usermaintenance.model;

import com.demo.usermaintenance.entity.User;
import org.mapstruct.Mapper;

/**
 * Mapper interface for converting between {@code User} entity and {@code UserDTO}.
 * Provides methods to map data bidirectionally between the entity and its corresponding data transfer object.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {
    /**
     * Converts a {@code User} entity to its corresponding {@code UserDTO}.
     *
     * @param user the {@code User} entity to be converted. Must not be null.
     * @return the {@code UserDTO} representation of the provided {@code User} entity.
     */
    UserDTO toDTO(User user);

    /**
     * Converts a {@code UserDTO} object to its corresponding {@code User} entity.
     *
     * @param userDTO the {@code UserDTO} object containing user details to be converted
     * @return the corresponding {@code User} entity
     */
    User toEntity(UserDTO userDTO);
}
