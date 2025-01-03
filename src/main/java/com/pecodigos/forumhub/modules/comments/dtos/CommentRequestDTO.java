package com.pecodigos.forumhub.modules.comments.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDTO(@NotNull Long id, @NotBlank @NotNull String content, @NotNull Long userId, @NotNull Long topicId) {
}
