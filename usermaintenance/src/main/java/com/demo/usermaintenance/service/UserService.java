package com.demo.usermaintenance.service;

import com.demo.usermaintenance.entity.User;
import com.demo.usermaintenance.model.UserDTO;
import com.demo.usermaintenance.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<UserDTO> searchByName(String name) {
        if (StringUtils.isBlank(name)) {
            return userRepository.findAll().stream().map(this::mapToUserDTO).toList();
        }
        return userRepository.findByNameContainingIgnoreCase(name).stream().map(this::mapToUserDTO).toList();
    }
    @Transactional
    public UserDTO save(@Valid UserDTO user) {
        return mapToUserDTO(userRepository.save(mapToUserEntity(user)));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO mapToUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).name(user.getName()).email(user.getEmail()).build();
    }

    private User mapToUserEntity(@Valid UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }
}
