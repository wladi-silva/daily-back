package com.wladi.daily.infrastructure.controllers.users;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wladi.daily.application.usecases.users.UserInteractor;
import com.wladi.daily.domain.entity.users.User;

@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserInteractor userInteractor;
    private final UserDtoMapper userDtoMapper;

    public UserController(UserInteractor userInteractor, UserDtoMapper userDtoMapper) {
        this.userInteractor = userInteractor;
        this.userDtoMapper = userDtoMapper;
    }

    @GetMapping("/{username}")
    public UserResponse user(@PathVariable String username) {
        User foundUser = userInteractor.findUser(username);
        return userDtoMapper.toResponse(foundUser);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        List<User> users = userInteractor.findAllUsers();
        return users.stream()
                    .map(userDtoMapper::toResponse)
                    .toList();
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        User userDomain = userDtoMapper.toUser(userRequest);
        User savedUser = userInteractor.createUser(userDomain);
        return userDtoMapper.toResponse(savedUser);
    }

    @PutMapping("/{username}")
    public UserResponse update(@PathVariable String username, @RequestBody UserRequest userRequest) {
        User userDomain = userDtoMapper.toUser(username, userRequest);
        User updatedUser = userInteractor.updateUser(userDomain);
        return userDtoMapper.toResponse(updatedUser);
    }

    @DeleteMapping("/{username}")
    public void delete(@PathVariable String username) {
        userInteractor.deleteUser(username);
    }

}
