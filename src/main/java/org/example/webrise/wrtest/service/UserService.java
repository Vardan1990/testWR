package org.example.webrise.wrtest.service;


import org.example.webrise.wrtest.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserEntity createUser(UserEntity user);

    Optional<UserEntity> getUserById(Long id);

    List<UserEntity> getAllUsers();

    UserEntity updateUser(Long id, UserEntity updatedUser);

    void deleteUser(Long id);
}
