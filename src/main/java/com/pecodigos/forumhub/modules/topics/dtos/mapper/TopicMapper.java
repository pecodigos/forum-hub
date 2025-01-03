package com.pecodigos.forumhub.modules.topics.dtos.mapper;

import com.pecodigos.forumhub.modules.topics.dtos.TopicResponseDTO;
import com.pecodigos.forumhub.modules.topics.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface TopicMapper {
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "state", target = "state")
    TopicResponseDTO toResponseDTO(Topic topic);
}
