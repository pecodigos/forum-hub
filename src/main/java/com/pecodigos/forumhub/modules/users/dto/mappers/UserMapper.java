package com.pecodigos.forumhub.modules.users.dto.mappers;

import com.pecodigos.forumhub.modules.users.dto.UserResponseDTO;
import com.pecodigos.forumhub.modules.users.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {
    @Mapping(source = "username", target = "username")
    @Mapping(source = "email", target = "email")
    UserResponseDTO toResponseDTO(User user);
}
