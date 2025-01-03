package com.pecodigos.forumhub.modules.users.service;

import com.pecodigos.forumhub.config.auth.JwtUtil;
import com.pecodigos.forumhub.modules.exceptions.EmailAlreadyExistsException;
import com.pecodigos.forumhub.modules.exceptions.InvalidPasswordException;
import com.pecodigos.forumhub.modules.exceptions.UserNotFoundException;
import com.pecodigos.forumhub.modules.exceptions.UsernameAlreadyExistsException;
import com.pecodigos.forumhub.modules.users.dto.UserRequestDTO;
import com.pecodigos.forumhub.modules.users.dto.UserResponseDTO;
import com.pecodigos.forumhub.modules.users.dto.mappers.UserMapper;
import com.pecodigos.forumhub.modules.users.entity.User;
import com.pecodigos.forumhub.modules.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserResponseDTO login(UserRequestDTO userRequestDTO) {
        var optionalUser = userRepository.findByUsername(userRequestDTO.username());

        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User not found.");
        }

        var user = optionalUser.get();
        if (!passwordEncoder.matches(userRequestDTO.password(), user.getPassword())) {
            throw new InvalidPasswordException("Invalid password.");
        }

        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO create(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUsername(userRequestDTO.username())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }

        if (userRepository.existsByEmail(userRequestDTO.email())) {
            throw new EmailAlreadyExistsException("E-mail already exists.");
        }

        var user = User.builder()
                .name(userRequestDTO.name())
                .username(userRequestDTO.username())
                .email(userRequestDTO.email())
                .password(passwordEncoder.encode(userRequestDTO.password()))
                .build();

        return userMapper.toResponseDTO(userRepository.save(user));
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public Map<Object, Object> tokenLogin(UserResponseDTO userResponseDTO) {
        String token = jwtUtil.generateToken(userResponseDTO.username());
        var response = new HashMap<>();

        response.put("token", token);
        response.put("user", new UserResponseDTO(userResponseDTO.username(), userResponseDTO.email()));

        return response;
    }
}
