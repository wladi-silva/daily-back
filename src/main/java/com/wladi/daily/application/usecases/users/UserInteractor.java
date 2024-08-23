package com.wladi.daily.application.usecases.users;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wladi.daily.application.usecases.users.gateways.UserGateway;
import com.wladi.daily.domain.entity.users.User;

@Service
public class UserInteractor {

    private UserGateway userGateway;
    
    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {
        return userGateway.createUser(user);
    }

    public User updateUser(User user) {
        return userGateway.updateUser(user);
    }

    public void deleteUser(String username) {
        userGateway.deleteUser(username);
    }

    public List<User> findAllUsers() {
        return userGateway.findAllUsers();
    }

    public User findUser(String username) {
        return userGateway.findUser(username);
    }

}