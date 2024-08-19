package com.wladi.daily.infrastructure.gateways;

import org.springframework.stereotype.Component;
import com.wladi.daily.application.usecases.gateways.UserGateway;
import com.wladi.daily.domain.entity.User;
import com.wladi.daily.infrastructure.persistences.UserEntity;
import com.wladi.daily.infrastructure.persistences.UserRepository;

@Component
public class UserRepositoryGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = userEntityMapper.toEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return userEntityMapper.toUserDomain(savedUserEntity);
    }

}