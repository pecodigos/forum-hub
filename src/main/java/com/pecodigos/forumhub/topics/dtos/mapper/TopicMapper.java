package com.pecodigos.forumhub.topics.dtos.mapper;

import com.pecodigos.forumhub.topics.dtos.TopicDTO;
import com.pecodigos.forumhub.topics.entity.Topic;
import org.mapstruct.Mapper;

@Mapper
public interface TopicMapper {
    TopicDTO toDTO(Topic topic);
    Topic toEntity(TopicDTO topicDTO);
}
