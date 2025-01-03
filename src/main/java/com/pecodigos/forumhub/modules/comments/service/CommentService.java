package com.pecodigos.forumhub.modules.comments.service;

import com.pecodigos.forumhub.modules.comments.dtos.CommentRequestDTO;
import com.pecodigos.forumhub.modules.comments.dtos.CommentResponseDTO;
import com.pecodigos.forumhub.modules.comments.dtos.mapper.CommentMapper;
import com.pecodigos.forumhub.modules.comments.entity.Comment;
import com.pecodigos.forumhub.modules.comments.repository.CommentRepository;
import com.pecodigos.forumhub.modules.exceptions.*;
import com.pecodigos.forumhub.modules.topics.repository.TopicRepository;
import com.pecodigos.forumhub.modules.users.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    public CommentResponseDTO createComment(Long topicId, CommentRequestDTO commentRequestDTO, String username) {
        if (commentRequestDTO.content().isBlank()) {
            throw new CommentContentCannotBeBlankException("Comment content cannot be blank.");
        }

        var topic = topicRepository.findById(topicId).orElseThrow(() -> new TopicNotFoundException("Topic not found with that ID."));
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with that username."));

        var comment = Comment.builder()
                .content(commentRequestDTO.content())
                .topic(topic)
                .user(user)
                .build();

        return commentMapper.toResponseDTO(commentRepository.save(comment));
    }

    public CommentResponseDTO updateComment(Long id, CommentRequestDTO commentRequestDTO, String username) {
        var comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found."));
        var user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found."));

        if (!comment.getUser().equals(user)) {
            throw new UserNotAllowedException("Comment does not belong to user.");
        }

        comment.setContent(commentRequestDTO.content());
        return commentMapper.toResponseDTO(commentRepository.save(comment));
    }
}
