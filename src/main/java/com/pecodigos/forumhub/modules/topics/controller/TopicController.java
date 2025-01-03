package com.pecodigos.forumhub.modules.topics.controller;

import com.pecodigos.forumhub.modules.topics.dtos.TopicRequestDTO;
import com.pecodigos.forumhub.modules.topics.dtos.TopicResponseDTO;
import com.pecodigos.forumhub.modules.topics.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/topics")
public class TopicController {

    private TopicService topicService;

    @GetMapping("/")
    public ResponseEntity<List<TopicResponseDTO>> findAll() {
        return ResponseEntity.ok(topicService.getAllTopics());
    }

    @GetMapping("/oldest-10")
    public ResponseEntity<List<TopicResponseDTO>> findTenOldest() {
        return ResponseEntity.ok(topicService.getTenOldestTopics());
    }

    @PostMapping("/")
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody TopicRequestDTO topicRequestDTO, Principal principal) {
        return ResponseEntity.ok(topicService.createTopic(topicRequestDTO, principal.getName()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TopicResponseDTO> updateTopic(@PathVariable Long id, @RequestBody TopicRequestDTO topicRequestDTO, Principal principal) {
        return ResponseEntity.ok(topicService.updateTopic(id, topicRequestDTO, principal.getName()));
    }
}
