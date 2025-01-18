package com.cbseassignment.concertmanagementsystem.mapper;

import com.cbseassignment.concertmanagementsystem.model.dto.UserDTO;
import com.cbseassignment.concertmanagementsystem.model.entity.User;

public class UserMapper {
    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setName(dto.getName());
        return user;
    }
}
