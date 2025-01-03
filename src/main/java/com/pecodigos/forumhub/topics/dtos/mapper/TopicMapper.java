package com.pecodigos.forumhub.topics.dtos.mapper;

import com.pecodigos.forumhub.topics.dtos.TopicResponseDTO;
import com.pecodigos.forumhub.topics.entity.Topic;
import org.mapstruct.Mapper;

@Mapper
public interface TopicMapper {
    TopicResponseDTO toResponseDTO(Topic topic);
}
