package com.pecodigos.forumhub.topics.service;

import com.pecodigos.forumhub.topics.dtos.TopicRequestDTO;
import com.pecodigos.forumhub.topics.dtos.TopicResponseDTO;
import com.pecodigos.forumhub.topics.dtos.mapper.TopicMapper;
import com.pecodigos.forumhub.topics.entity.Topic;
import com.pecodigos.forumhub.topics.enums.TopicState;
import com.pecodigos.forumhub.topics.repository.TopicRepository;
import com.pecodigos.forumhub.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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
            throw new IllegalArgumentException("Title already exists.");
        }

        if (topicRepository.existsByContent(topicRequestDTO.content())) {
            throw new IllegalArgumentException("Content already exists.");
        }

        var user = userRepository.findByUsername(username).orElseThrow(() -> new NoSuchElementException("User not found."));

        var topic = Topic.builder()
                .title(topicRequestDTO.title())
                .content(topicRequestDTO.content())
                .state(TopicState.OPENED)
                .user(user)
                .build();

        return topicMapper.toResponseDTO(topicRepository.save(topic));
    }

    public TopicResponseDTO updateTopic(Long id, TopicRequestDTO topicRequestDTO, String username) {
        var topic = topicRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Topic not found."));

        if (!topic.getUser().getUsername().equals(username)) {
            throw new IllegalArgumentException("User not allowed to update this topic.");
        }

        topic.setTitle(topicRequestDTO.title());
        topic.setContent(topicRequestDTO.content());
        topic.setState(topicRequestDTO.state());

        return topicMapper.toResponseDTO(topicRepository.save(topic));
    }
}
