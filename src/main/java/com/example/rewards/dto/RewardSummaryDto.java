
package com.example.rewards.dto;

import java.util.Map;

/**
 * DTO representing monthly and total reward points for a customer.
 */
public class RewardSummaryDto {

    private final String customerId;
    private final Map<String, Integer> monthlyPoints;
    private final int totalPoints;

    public RewardSummaryDto(String customerId, Map<String, Integer> monthlyPoints, int totalPoints) {
        this.customerId = customerId;
        this.monthlyPoints = monthlyPoints;
        this.totalPoints = totalPoints;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Map<String, Integer> getMonthlyPoints() {
        return monthlyPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }
}
