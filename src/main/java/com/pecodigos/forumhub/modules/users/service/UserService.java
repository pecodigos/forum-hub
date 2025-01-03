package com.pecodigos.forumhub.modules.users.service;

import com.pecodigos.forumhub.modules.exceptions.UserNotFoundException;
import com.pecodigos.forumhub.modules.users.dto.UserResponseDTO;
import com.pecodigos.forumhub.modules.users.dto.mappers.UserMapper;
import com.pecodigos.forumhub.modules.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserResponseDTO getUserById(UUID id) {
        return userRepository.findById(id).
                map(userMapper::toResponseDTO)
                .orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
}
