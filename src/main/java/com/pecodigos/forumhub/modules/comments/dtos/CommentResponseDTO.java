package com.pecodigos.forumhub.modules.comments.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentResponseDTO(@NotBlank @NotNull String content) {
}
