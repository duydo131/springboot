package com.learnspringboot.demo.dto.mapper;

import com.learnspringboot.demo.dto.UserInfoDTO;
import com.learnspringboot.demo.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserInfoDTO userToUserInfoDTO(User user);
}
