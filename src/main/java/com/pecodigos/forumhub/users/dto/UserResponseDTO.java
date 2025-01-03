package com.pecodigos.forumhub.users.dto;

import jakarta.validation.constraints.NotNull;

public record UserResponseDTO(@NotNull String username, @NotNull String email) {
}
