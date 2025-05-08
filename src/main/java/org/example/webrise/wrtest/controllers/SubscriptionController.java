package org.example.webrise.wrtest.controllers;

import lombok.RequiredArgsConstructor;
import org.example.webrise.wrtest.entity.SubscriptionEntity;
import org.example.webrise.wrtest.service.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/{userId}")
    public ResponseEntity<SubscriptionEntity> addSubscription(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        return ResponseEntity.ok(subscriptionService.addSubscription(userId, request.get("subName")));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<SubscriptionEntity>> getUserSubscriptions(@PathVariable Long userId) {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptions(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long userId, @RequestBody Map<String, String> request) {
        subscriptionService.deleteSubscription(request.get("subName"), userId);
        return ResponseEntity.noContent().build();
    }
}