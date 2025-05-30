package org.example.webrise.wrtest.service;


import org.example.webrise.wrtest.dto.GetSubscriptionStatsDto;
import org.example.webrise.wrtest.entity.SubscriptionEntity;

import java.util.List;

public interface SubscriptionService {

    SubscriptionEntity addSubscription(Long userId, String serviceName);

    List<SubscriptionEntity> getUserSubscriptions(Long userId);

    void deleteSubscription(String serviceName,Long subscriptionId);

    List<GetSubscriptionStatsDto> getTop3Subscriptions();
}
