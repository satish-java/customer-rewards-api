
package com.example.rewards.controller;

import com.example.rewards.dto.RewardSummaryDto;
import com.example.rewards.model.Transaction;
import com.example.rewards.service.RewardsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller for rewards API.
 */
@RestController
@RequestMapping("/api")
public class RewardsController {

    private final RewardsService rewardsService;

    public RewardsController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping(path = "/rewards",  produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RewardSummaryDto> getRewards() {

        List<Transaction> transactions = List.of(
                new Transaction("CUST1", 120, LocalDate.now().minusMonths(2)),
                new Transaction("CUST1", 75, LocalDate.now().minusMonths(1)),
                new Transaction("CUST1", 200, LocalDate.now()),
                new Transaction("CUST2", 90, LocalDate.now().minusMonths(1)),
                new Transaction("CUST2", 130, LocalDate.now())
        );

        return rewardsService.calculateRewards(transactions);
    }
}
