package com.example.rewards.controller;

import com.example.rewards.model.CustomerReward;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardsAPIService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for rewards API.
 */

@Slf4j
@RestController
@RequestMapping("/api")
public class RewardsAPIController {

    @Autowired
    private RewardsAPIService service;

    /**
     * Saves new rewards api transaction into the system.
     *
     */
    @PostMapping("/rewards/saveRewards")
    public ResponseEntity<Transaction> saveData(@RequestBody Transaction request)
    {
        Transaction saved = service.saveRewardTransaction(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    /**
     * Gets rewards api details for a customer.
     *
     */
    @GetMapping("/rewards/{customerId}")
    public ResponseEntity<CustomerReward> getRewards(@PathVariable Long customerId)
    {
        log.info("RewardsController getRewards request started for customerId={}", customerId);
        CustomerReward reward = service.getRewardsForCustomer(customerId);
        log.info("RewardsController getRewards completed successfully for customerId={}, reward={}",
                customerId, reward);
        return ResponseEntity.ok(reward);
    }
}
