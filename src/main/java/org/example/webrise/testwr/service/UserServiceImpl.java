package org.example.webrise.testwr.service;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrise.testwr.entity.UserEntity;
import org.example.webrise.testwr.exceptions.UserException;
import org.example.webrise.testwr.repo.UserRepository;
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
    public UserEntity createUser(UserEntity user) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new UserException("user by this emil already exist");
        }
        user.setCreated(LocalDateTime.now());
        user.setUpdated(LocalDateTime.now());
        log.info("Create user by email : {}", user.getEmail());
        return userRepository.save(user);
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
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
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
