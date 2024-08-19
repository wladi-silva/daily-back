package com.wladi.daily.infrastructure.controllers;

import org.springframework.stereotype.Component;

import com.wladi.daily.domain.entity.User;

@Component
public class UserDtoMapper {
    
    public User toUser(UserRequest userRequest) {
        return new User(
            userRequest.name(),
            userRequest.username(),
            userRequest.email(),
            userRequest.password()
        );
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.name(), 
            user.username(), 
            user.email()
        );
    }

}