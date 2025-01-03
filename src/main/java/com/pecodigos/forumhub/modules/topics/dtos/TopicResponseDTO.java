package com.pecodigos.forumhub.modules.topics.dtos;

import com.pecodigos.forumhub.modules.topics.enums.TopicState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicResponseDTO(
        @NotBlank @NotNull String title,
        @NotBlank @NotNull String content,
        @NotNull TopicState state
) {
}
