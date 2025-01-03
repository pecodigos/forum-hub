package com.pecodigos.forumhub.modules.comments.controller;

import com.pecodigos.forumhub.modules.comments.dtos.CommentRequestDTO;
import com.pecodigos.forumhub.modules.comments.dtos.CommentResponseDTO;
import com.pecodigos.forumhub.modules.comments.service.CommentService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/create")
    public ResponseEntity<CommentResponseDTO> createComment(@PathVariable(name = "id") Long topicId, @RequestBody CommentRequestDTO commentRequestDTO, Principal principal) {
        return ResponseEntity.ok(commentService.createComment(topicId, commentRequestDTO, principal.getName()));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateComment(@PathVariable(name = "id") Long id, @RequestBody CommentRequestDTO commentRequestDTO, Principal principal) {
        return ResponseEntity.ok(commentService.updateComment(id, commentRequestDTO, principal.getName()));
    }
}
