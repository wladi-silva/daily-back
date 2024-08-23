package com.wladi.daily.application.usecases.users.gateways;

import java.util.List;

import com.wladi.daily.domain.entity.users.User;

public interface UserGateway {

    User createUser(User user);
    User updateUser(User user);
    void deleteUser(String username);
    User findUser(String username);
    List<User> findAllUsers();

}