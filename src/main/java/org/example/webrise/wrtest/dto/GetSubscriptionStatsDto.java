package org.example.webrise.wrtest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetSubscriptionStatsDto {
    private String serviceName;
    private Long count;
}