
package com.example.rewards.service;

import com.example.rewards.dto.RewardSummaryDto;
import com.example.rewards.exception.InvalidTransactionException;
import com.example.rewards.model.Transaction;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service responsible for reward calculation.
 */
@Service
public class RewardsService {

    public List<RewardSummaryDto> calculateRewards(List<Transaction> transactions) {

        Map<String, Map<Month, Integer>> rewards = new HashMap<>();

        for (Transaction tx : transactions) {
            validate(tx);

            int points = calculatePoints(tx.getAmount());
            Month month = tx.getTransactionDate().getMonth();

            rewards.computeIfAbsent(tx.getCustomerId(), k -> new HashMap<>())
                   .merge(month, points, Integer::sum);
        }

        return rewards.entrySet().stream()
                .map(entry -> {
                    Map<String, Integer> monthly = entry.getValue().entrySet()
                            .stream()
                            .collect(Collectors.toMap(e -> e.getKey().name(), Map.Entry::getValue));

                    int total = monthly.values().stream().mapToInt(Integer::intValue).sum();

                    return new RewardSummaryDto(entry.getKey(), monthly, total);
                })
                .toList();
    }

    public int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }

    private void validate(Transaction tx) {
        if (tx.getAmount() < 0) {
            throw new InvalidTransactionException("Transaction amount cannot be negative");
        }
    }
}
