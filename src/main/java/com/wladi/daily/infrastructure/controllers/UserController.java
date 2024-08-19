package com.wladi.daily.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wladi.daily.application.usecases.UserInteractor;
import com.wladi.daily.domain.entity.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserInteractor userInteractor;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserInteractor userInteractor, UserDtoMapper userDtoMapper) {
        this.userInteractor = userInteractor;
        this.userDtoMapper = userDtoMapper;
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        User userDomain = userDtoMapper.toUser(userRequest);
        User savedUser = userInteractor.createUser(userDomain);
        return userDtoMapper.toResponse(savedUser);
    }
    
}
