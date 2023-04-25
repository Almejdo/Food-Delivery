package com.fooddelivery.components.user.dto.mapper;



import com.fooddelivery.components.user.dto.UserDto;
import com.fooddelivery.components.user.dto.UserUpdateDto;
import com.fooddelivery.components.user.entity.User;

import java.time.LocalDateTime;


public class UserMapper {

    public static UserDto toDto(User u){
        return UserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .username(u.getUsername())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .password(u.getPassword())
                .createdAt(LocalDateTime.now())
                .build();

    }

    public static User toEntity(UserDto u){
        return User.builder()
                .name(u.getName())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .username(u.getUsername())
                .password(u.getPassword())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static UserUpdateDto toUpdateDto(User u){
        return UserUpdateDto.builder()
                .id(u.getId())
                .name(u.getName())
                .username(u.getUsername())
                .phoneNumber(u.getPhoneNumber())
                .email(u.getEmail())
                .build();
    }

    public static User buildUpdateUser(User u, UserUpdateDto req){
        u.setName(req.getName());
        u.setPhoneNumber(req.getPhoneNumber());
        u.setEmail(req.getEmail());
        u.setUsername(req.getUsername());
        return u;
    }


}
