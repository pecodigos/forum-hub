package com.pecodigos.forumhub.modules.topics.service;

import com.pecodigos.forumhub.modules.exceptions.*;
import com.pecodigos.forumhub.modules.topics.dtos.TopicRequestDTO;
import com.pecodigos.forumhub.modules.topics.dtos.TopicResponseDTO;
import com.pecodigos.forumhub.modules.topics.dtos.mapper.TopicMapper;
import com.pecodigos.forumhub.modules.topics.entity.Topic;
import com.pecodigos.forumhub.modules.topics.enums.TopicState;
import com.pecodigos.forumhub.modules.topics.repository.TopicRepository;
import com.pecodigos.forumhub.modules.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TopicService {

    private TopicRepository topicRepository;
    private UserRepository userRepository;
    private TopicMapper topicMapper;

    public List<TopicResponseDTO> getAllTopics() {
        return topicRepository.findAll().stream()
                .map(topicMapper::toResponseDTO)
                .toList();
    }

    public List<TopicResponseDTO> getTenOldestTopics() {
        return topicRepository.findTenOldestTopics(PageRequest.of(0, 10)).stream()
                .map(topicMapper::toResponseDTO)
                .toList();
    }

    public TopicResponseDTO createTopic(TopicRequestDTO topicRequestDTO, String username) {
        if (topicRepository.existsByTitle(topicRequestDTO.title())) {
            throw new TopicTitleAlreadyExistsException("Title already exists.");
        }

        if (topicRepository.existsByContent(topicRequestDTO.content())) {
            throw new TopicContentAlreadyExistsException("Content already exists.");
        }

        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));

        var topic = Topic.builder()
                .title(topicRequestDTO.title())
                .content(topicRequestDTO.content())
                .state(TopicState.OPENED)
                .user(user)
                .build();

        return topicMapper.toResponseDTO(topicRepository.save(topic));
    }

    public TopicResponseDTO updateTopic(Long id, TopicRequestDTO topicRequestDTO, String username) {
        var topic = topicRepository.findById(id).orElseThrow(() -> new TopicNotFoundException("Topic not found."));

        if (!topic.getUser().getUsername().equals(username)) {
            throw new UserNotAllowedException("User not allowed to update this topic.");
        }

        topic.setTitle(topicRequestDTO.title());
        topic.setContent(topicRequestDTO.content());
        topic.setState(topicRequestDTO.state());

        return topicMapper.toResponseDTO(topicRepository.save(topic));
    }
}
