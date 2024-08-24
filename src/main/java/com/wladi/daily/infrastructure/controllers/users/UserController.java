package com.wladi.daily.infrastructure.controllers.users;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserResponse> getUser(@PathVariable String username) {
        User foundUser = userInteractor.findUser(username);
        return ResponseEntity.status(HttpStatus.OK).body(userDtoMapper.toResponse(foundUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUser() {
        List<User> users = userInteractor.findAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(users.stream()
            .map(userDtoMapper::toResponse)
            .toList());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        User userDomain = userDtoMapper.toUser(userRequest);
        User savedUser = userInteractor.createUser(userDomain);
        return ResponseEntity.status(HttpStatus.CREATED).body(userDtoMapper.toResponse(savedUser));
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable String username, @RequestBody UserRequest userRequest) {
        User userDomain = userDtoMapper.toUser(username, userRequest);
        User updatedUser = userInteractor.updateUser(userDomain);
        return ResponseEntity.status(HttpStatus.OK).body(userDtoMapper.toResponse(updatedUser));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userInteractor.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}
