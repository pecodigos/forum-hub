package com.pecodigos.forumhub.modules.users.controller;

import com.pecodigos.forumhub.modules.users.dto.UserRequestDTO;
import com.pecodigos.forumhub.modules.users.dto.UserResponseDTO;
import com.pecodigos.forumhub.modules.users.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            var loggedInUser = authService.login(userRequestDTO);

            return ResponseEntity.ok(authService.tokenLogin(loggedInUser));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.create(userRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
