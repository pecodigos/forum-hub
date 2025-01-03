package com.pecodigos.forumhub.modules.comments.repository;

import com.pecodigos.forumhub.modules.comments.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
