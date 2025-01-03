package com.pecodigos.forumhub.modules.comments.dtos.mapper;

import com.pecodigos.forumhub.modules.comments.dtos.CommentResponseDTO;
import com.pecodigos.forumhub.modules.comments.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {
    @Mapping(source = "content", target = "content")
    CommentResponseDTO toResponseDTO(Comment comment);
}
