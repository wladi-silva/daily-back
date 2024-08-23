package com.wladi.daily.infrastructure.gateways.users;

import org.springframework.stereotype.Component;

import com.wladi.daily.domain.entity.users.User;
import com.wladi.daily.infrastructure.persistences.users.UserEntity;

@Component
public class UserEntityMapper {

    public UserEntity toEntity(User user) {
        return UserEntity.builder()
            .name(user.name())
            .username(user.username())
            .email(user.email())
            .password(user.password())
            .build();
    }

    public User toUserDomain(UserEntity userEntity) {
        return new User(
            userEntity.getName(),
            userEntity.getUsername(),
            userEntity.getEmail(),
            userEntity.getPassword()
        );
    }

}