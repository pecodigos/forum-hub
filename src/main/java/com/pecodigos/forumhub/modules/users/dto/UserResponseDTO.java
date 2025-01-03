package com.pecodigos.forumhub.modules.users.dto;

import jakarta.validation.constraints.NotNull;

public record UserResponseDTO(@NotNull String username, @NotNull String email) {
}
