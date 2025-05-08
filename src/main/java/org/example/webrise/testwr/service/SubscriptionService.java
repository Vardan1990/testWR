package org.example.webrise.testwr.service;

import org.example.webrise.testwr.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionService {

    SubscriptionEntity addSubscription(Long userId, String serviceName);

    List<SubscriptionEntity> getUserSubscriptions(Long userId);

    void deleteSubscription(String serviceName,Long subscriptionId);
}
