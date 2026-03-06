package com.demo.usermaintenance.api;

import com.demo.usermaintenance.model.UserDTO;
import com.demo.usermaintenance.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class responsible for handling API requests related to user operations.
 * This includes retrieving a list of users, creating a new user, and deleting a user
 * by their unique identifier. Acts as the RESTful API layer for user-related actions.
 */
@RequiredArgsConstructor
@RestController
@Transactional
@RequestMapping("/api/v1/user")
public class UserController implements UserAPI {

    private final UserService userService;

    @Override
    public List<UserDTO> getAllUsers(@RequestParam(required = false) String name) {
        return userService.searchByName(name);
    }

    @Override
    public UserDTO createUser(@Valid @RequestBody UserDTO user) {
        return userService.save(user);
    }

    @Override
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
