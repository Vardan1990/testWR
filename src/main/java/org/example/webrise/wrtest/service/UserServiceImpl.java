package org.example.webrise.wrtest.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.example.webrise.wrtest.dto.CreateUserDto;
import org.example.webrise.wrtest.dto.UpdateUserDto;
import org.example.webrise.wrtest.entity.UserEntity;
import org.example.webrise.wrtest.exceptions.UserException;
import org.example.webrise.wrtest.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(CreateUserDto user) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserException("user by this emil already exist");
        }
        UserEntity userEntity = UserEntity.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .build();
        log.info("Create user by email : {}", user.getEmail());
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getUserById(Long id) {
        log.info("Get user by id: {}", id);
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(Long id, UpdateUserDto updatedUser) {
        return userRepository.findById(id).map(user -> {
            boolean updated = false;

            if (!StringUtils.isBlank(updatedUser.getUserName())) {
                user.setUserName(updatedUser.getUserName());
                updated = true;
            }
            if (!StringUtils.isBlank(updatedUser.getEmail()) &&
                    userRepository.findByEmail(updatedUser.getEmail()).isEmpty()) {
                user.setEmail(updatedUser.getEmail());
                updated = true;
            }

            if (!updated) {
                return null;
            }
            user.setUpdated(LocalDateTime.now());
            return userRepository.save(user);
        }).orElseThrow(() -> new UserException("User not found"));
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
    }
}
