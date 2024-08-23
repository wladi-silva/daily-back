package com.wladi.daily.infrastructure.gateways.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.wladi.daily.application.usecases.users.exceptions.UserNotFoundException;
import com.wladi.daily.application.usecases.users.gateways.UserGateway;
import com.wladi.daily.domain.entity.users.User;
import com.wladi.daily.infrastructure.persistences.users.UserEntity;
import com.wladi.daily.infrastructure.persistences.users.UserRepository;

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

    @Override
    public User updateUser(User user) {
        UserEntity existingUserEntity = userRepository.findByUsername(user.username())
            .orElseThrow(() -> new RuntimeException("User not found with username: " + user.username()));

        existingUserEntity.setName(user.name());
        existingUserEntity.setUsername(user.username());
        existingUserEntity.setEmail(user.email());
        existingUserEntity.setPassword(user.password());

        UserEntity updatedUserEntity = userRepository.save(existingUserEntity);
        return userEntityMapper.toUserDomain(updatedUserEntity);
    }

    @Override
    public void deleteUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        userRepository.delete(userEntity);
    }

    @Override
    public User findUser(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));

        return userEntityMapper.toUserDomain(userEntity);
    }

    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream()
                            .map(userEntityMapper::toUserDomain)
                            .collect(Collectors.toList());
    }

}