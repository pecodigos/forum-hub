package com.pecodigos.forumhub.comments.service;

import com.pecodigos.forumhub.comments.dtos.CommentDTO;
import com.pecodigos.forumhub.comments.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentDTO createComment() {
        return null;
    }
}
