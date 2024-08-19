package com.wladi.daily.application.usecases;

import org.springframework.stereotype.Service;

import com.wladi.daily.application.usecases.gateways.UserGateway;
import com.wladi.daily.domain.entity.User;

@Service
public class UserInteractor {

    private UserGateway userGateway;
    
    public UserInteractor(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public User createUser(User user) {
        return userGateway.createUser(user);
    }

}