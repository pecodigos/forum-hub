package com.pecodigos.forumhub.topics.dtos;

import com.pecodigos.forumhub.topics.enums.TopicState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TopicRequestDTO(@NotNull Long id,
                              @NotBlank @NotNull String title,
                              @NotBlank @NotNull String content,
                              @NotNull TopicState state,
                              @NotNull UUID userId) {
}
