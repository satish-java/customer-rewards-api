package com.example.rewards.service;

import com.example.rewards.exception.CustomException;
import com.example.rewards.model.CustomerReward;
import com.example.rewards.model.MonthlyReward;
import com.example.rewards.model.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class RewardsAPIServiceTest {

    @Mock
    private TransactionRepository repository;

    @Mock
    private RewardsAPICalculator calculator;

    @InjectMocks
    private RewardsAPIService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRewardsForCustomer_Success() {
        Long customerId = 1L;
        LocalDate now = LocalDate.now();
        LocalDate within90Days = now.minusDays(30);
        List<Transaction> mockTransactions = List.of(
                new Transaction(1L,customerId, 120.0, within90Days),
                new Transaction(2L,customerId, 70.0, within90Days)
        );
        when(repository.findAll()).thenReturn(mockTransactions);
        when(calculator.calculatePoints(120.0)).thenReturn(90);
        when(calculator.calculatePoints(70.0)).thenReturn(20);
        CustomerReward reward = service.getRewardsForCustomer(customerId);
        assertNotNull(reward);
        assertEquals(customerId, reward.customerId());
        assertEquals(1, reward.monthlyRewards().size());
        MonthlyReward monthReward = reward.monthlyRewards().get(0);
        assertEquals(110, monthReward.points());
        assertEquals(110, reward.totalPoints());
    }

    @Test
    void testGetRewardsForCustomer_NoTransactions() {
        Long customerId = 999L;
        when(repository.findAll()).thenReturn(List.of());
        CustomException ex = assertThrows(
                CustomException.class,
                () -> service.getRewardsForCustomer(customerId)
        );
        assertEquals("Customer ID 999 not found.", ex.getMessage());
        verify(repository, times(1)).findAll();
        verifyNoInteractions(calculator);
    }
}