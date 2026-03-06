package com.demo.usermaintenance.service;

import com.demo.usermaintenance.model.UserDTO;
import com.demo.usermaintenance.model.UserMapper;
import com.demo.usermaintenance.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing user-related operations. Provides methods for
 * searching, saving, and deleting users.
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Searches for users by their name. If the provided name is blank or null, it returns all users.
     * The search is case-insensitive and matches any part of the name.
     *
     * @param name the name or part of the name to search for. If blank or null, all users are returned.
     * @return a list of {@code UserDTO} objects that match the search criteria. Returns all users if the name is not provided.
     */
    public List<UserDTO> searchByName(String name) {
        if (StringUtils.isBlank(name)) {
            return userRepository.findAll().stream().map(userMapper::toDTO).toList();
        }
        return userRepository.findByNameContainingIgnoreCase(name).stream().map(userMapper::toDTO).toList();
    }

    /**
     * Persists a new user or updates an existing user in the system.
     *
     * @param user the {@code UserDTO} object containing the user's details to be saved.
     *             The input is validated to ensure required fields are provided.
     * @return the saved {@code UserDTO} object with updated information, such as generated identifiers.
     */
    public UserDTO save(@Valid UserDTO user) {
        return userMapper.toDTO(userRepository.save(userMapper.toEntity(user)));
    }

    /**
     * Deletes a user from the system based on the provided unique identifier.
     *
     * @param id the unique identifier of the user to be deleted. Must not be null.
     */
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
