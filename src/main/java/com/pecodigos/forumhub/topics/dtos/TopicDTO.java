package com.pecodigos.forumhub.topics.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicDTO(@NotNull Long id,
                       @NotBlank @NotNull String title,
                       @NotBlank @NotNull String content) {
}
