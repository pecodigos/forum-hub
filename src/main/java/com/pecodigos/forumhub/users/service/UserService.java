package com.pecodigos.forumhub.users.service;

import com.pecodigos.forumhub.users.dto.UserResponseDTO;
import com.pecodigos.forumhub.users.dto.mappers.UserMapper;
import com.pecodigos.forumhub.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserResponseDTO getUserById(UUID id) {
        return userRepository.findById(id).
                map(userMapper::toResponseDTO)
                .orElseThrow(() -> new NoSuchElementException("User not found."));
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toResponseDTO)
                .toList();
    }
}
