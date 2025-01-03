package com.pecodigos.forumhub.config.mapper;

import com.pecodigos.forumhub.modules.comments.dtos.mapper.CommentMapper;
import com.pecodigos.forumhub.modules.topics.dtos.mapper.TopicMapper;
import com.pecodigos.forumhub.modules.users.dto.mappers.UserMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserMapper userMapper() {
        return Mappers.getMapper(UserMapper.class);
    }

    @Bean
    public TopicMapper topicMapper() {
        return Mappers.getMapper(TopicMapper.class);
    }

    @Bean
    public CommentMapper commentMapper() {
        return Mappers.getMapper(CommentMapper.class);
    }
}
