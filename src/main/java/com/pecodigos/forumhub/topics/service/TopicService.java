package com.pecodigos.forumhub.topics.service;

import com.pecodigos.forumhub.topics.dtos.TopicDTO;
import com.pecodigos.forumhub.topics.dtos.mapper.TopicMapper;
import com.pecodigos.forumhub.topics.entity.Topic;
import com.pecodigos.forumhub.topics.enums.TopicState;
import com.pecodigos.forumhub.topics.repository.TopicRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {

    private TopicRepository topicRepository;
    private TopicMapper topicMapper;

    public List<TopicDTO> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toDTO)
                .toList();
    }

    public List<TopicDTO> getTenOldestTopics() {
        return topicRepository.findTenOldestTopics(PageRequest.of(0, 10)).stream()
                .map(topicMapper::toDTO)
                .toList();
    }

    public TopicDTO createTopic(TopicDTO topicDTO) {
        if (topicRepository.existsByTitle(topicDTO.title())) {
            throw new IllegalArgumentException("Title already exists.");
        }

        if (topicRepository.existsByContent(topicDTO.content())) {
            throw new IllegalArgumentException("Content already exists.");
        }

        var topic = Topic.builder()
                .title(topicDTO.title())
                .content(topicDTO.content())
                .state(TopicState.OPENED)
                .build();

        return topicMapper.toDTO(topicRepository.save(topic));
    }
}
