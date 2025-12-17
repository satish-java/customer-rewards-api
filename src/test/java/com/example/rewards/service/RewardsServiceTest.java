
package com.example.rewards.service;

import com.example.rewards.exception.InvalidTransactionException;
import com.example.rewards.model.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RewardsService.
 */
class RewardsServiceTest {

    private final RewardsService service = new RewardsService();

    @Test
    void calculatePoints_shouldReturnCorrectValues() {
        assertEquals(90, service.calculatePoints(120));
        assertEquals(25, service.calculatePoints(75));
        assertEquals(0, service.calculatePoints(40));
    }

    @Test
    void calculateRewards_shouldThrowExceptionForNegativeAmount() {
        Transaction tx = new Transaction("C1", -10, LocalDate.now());
        assertThrows(InvalidTransactionException.class,
                () -> service.calculateRewards(List.of(tx)));
    }
}
