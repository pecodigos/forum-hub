package com.pecodigos.forumhub.comments.repository;

import com.pecodigos.forumhub.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
