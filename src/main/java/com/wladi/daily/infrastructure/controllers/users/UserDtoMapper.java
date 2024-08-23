package com.wladi.daily.infrastructure.controllers.users;

import org.springframework.stereotype.Component;

import com.wladi.daily.domain.entity.users.User;

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

    public User toUser(String username, UserRequest userRequest) {
        return new User(
            username,
            userRequest.name(),
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