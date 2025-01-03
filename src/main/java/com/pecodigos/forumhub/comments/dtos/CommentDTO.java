package com.pecodigos.forumhub.comments.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentDTO(@NotNull Long id, @NotBlank @NotNull String content, @NotNull Long userId, @NotNull Long topicId) {
}
