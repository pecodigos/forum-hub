package com.pecodigos.forumhub.topics.controller;

import com.pecodigos.forumhub.topics.dtos.TopicDTO;
import com.pecodigos.forumhub.topics.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private TopicService topicService;

    @GetMapping("/")
    public ResponseEntity<List<TopicDTO>> findAll() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/oldest")
    public ResponseEntity<List<TopicDTO>> findTenOldest() {
        return ResponseEntity.ok(topicService.getTenOldestTopics());
    }

    @PostMapping("/")
    public ResponseEntity<TopicDTO> createTopic(@RequestBody TopicDTO topicDTO) {
        return ResponseEntity.ok(topicService.createTopic(topicDTO));
    }
}
