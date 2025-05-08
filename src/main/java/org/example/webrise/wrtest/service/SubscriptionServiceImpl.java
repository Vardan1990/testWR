package org.example.webrise.wrtest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.webrise.wrtest.entity.SubscriptionEntity;
import org.example.webrise.wrtest.entity.UserEntity;
import org.example.webrise.wrtest.exceptions.SubscriptionException;
import org.example.webrise.wrtest.exceptions.UserException;
import org.example.webrise.wrtest.repo.SubscriptionsRepository;
import org.example.webrise.wrtest.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionsRepository subscriptionsRepository;
    private final UserRepository userRepository;


    @Override
    public SubscriptionEntity addSubscription(Long userId, String serviceName) {
        if (userId == null || serviceName.isEmpty()) {
            throw new SubscriptionException("Service name is null");
        }
        Optional<SubscriptionEntity> optionalSubscription = subscriptionsRepository.findBySubNameAndUserEntity(serviceName, userId);
        if (optionalSubscription.isPresent()) {
            throw new SubscriptionException("Subscription on service  by this id already exist");
        }
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User not found"));
        SubscriptionEntity subscription = SubscriptionEntity.builder()
                .subName(serviceName)
                .userEntity(user)
                .build();
        log.info("Successfully create subscription on :{} by id : {}", serviceName, userId);
        return subscriptionsRepository.save(subscription);
    }


    @Override
    public List<SubscriptionEntity> getUserSubscriptions(Long userId) {
        log.info("Get all subscriptions for user id: {}", userId);
        return subscriptionsRepository.findByUserEntity(userId);
    }

    @Override
    public void deleteSubscription(String serviceName, Long userId) {
        log.info("Delete subscription with id: {}", userId);
        Optional<SubscriptionEntity> optionalSubscription = subscriptionsRepository.findBySubNameAndUserEntity(serviceName, userId);
        optionalSubscription.ifPresent(subscriptionsRepository::delete);
    }
}
