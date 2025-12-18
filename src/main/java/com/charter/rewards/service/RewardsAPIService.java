package com.charter.rewards.service;

import com.charter.rewards.exception.CustomException;
import com.charter.rewards.model.CustomerReward;
import com.charter.rewards.model.MonthlyReward;
import com.charter.rewards.model.Transaction;
import com.charter.rewards.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for reward calculation.
 */

@Slf4j
@Service
public class RewardsAPIService {
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private RewardsAPICalculator calculator;

    public Transaction saveRewardTransaction(Transaction request) {
        log.info("RewardsService saveTransaction started. Saving transaction request: {}", request);
        return repository.save(request);
    }

    public CustomerReward getRewardsForCustomer(Long customerId) {

        log.info("Fetching reward transactions for customerId={}", customerId);
        LocalDate now = LocalDate.now();
        LocalDate start = now.minusDays(90); // last 90 days
        List<Transaction> userTransactions = repository.findAll().stream()
                .filter(t -> t.getCustomerId().equals(customerId))
                .filter(t -> !t.getDate().isBefore(start) && !t.getDate().isAfter(now))
                .toList();

        if (userTransactions.isEmpty()) {
            log.error("No transactions found for customerId: {}", customerId);
            throw new CustomException("Customer ID " + customerId + " not found.");
        }
        log.info("Found {} transactions for customerId: {}", userTransactions.size(), customerId);
        Map<String, Integer> monthlyPoints = new HashMap<>();

        for (Transaction transaction : userTransactions) {
            int points = calculator.calculatePoints(transaction.getAmount());
            String month = transaction.getDate().getMonth().toString();
            log.info("Reward Transaction, amount={}, month={}, calculatedPoints={}",
                    transaction.getAmount(), month, points);
            monthlyPoints.merge(month, points, Integer::sum);
        }
        List<MonthlyReward> rewards = monthlyPoints.entrySet().stream()
                .map(e -> new MonthlyReward(e.getKey(), e.getValue()))
                .toList();

        int total = rewards.stream().mapToInt(MonthlyReward::points).sum();
        log.info("Exiting getRewardsForCustomer with totalRewardPoints={} for customerId={}",
                total, customerId);
        return new CustomerReward(customerId, rewards, total);
    }
}