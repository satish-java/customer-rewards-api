package com.example.rewards.service;

import com.example.rewards.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Helper class used to calculate reward points based on purchase amount.
 */

@Slf4j
@Component
public class RewardsAPICalculator
{
    public int calculatePoints(double amount){
        log.debug("RewardsCalculator calculatePoints started for transaction amount={}", amount);
        if (amount < 0)
            throw new CustomException("Reward Amount cannot be negative");

        int points = 0;
        if(amount > 100){
            points += ((amount - 100) * 2);
            amount = 100;
        }
        if(amount > 50){
            points += ((amount - 50) * 1);
        }
        log.info("RewardsCalculator calculatePoints completed for amount={}, calculatedPoints={}",
                amount, points);
        return points;
    }
}