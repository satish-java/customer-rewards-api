package com.charter.rewards.controller;

import com.charter.rewards.model.CustomerReward;
import com.charter.rewards.model.MonthlyReward;
import com.charter.rewards.service.RewardsAPIService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class RewardsAPIControllerTest {

    @Mock
    private RewardsAPIService service;

    @InjectMocks
    private RewardsAPIController controller;

    @Test
    void testGetRewardsSuccess() {

        MonthlyReward mRewards=new MonthlyReward("SEPTEMBER",120);
        List<MonthlyReward> monthlyRewards=new ArrayList<>();
        monthlyRewards.add(mRewards);
        CustomerReward customerRewards=new CustomerReward(1l,monthlyRewards,120);
        when(service.getRewardsForCustomer(1L)).thenReturn(customerRewards);
        ResponseEntity<CustomerReward> rewards = controller.getRewards(1L);
        assertNotNull(rewards);
        assertEquals(1l,rewards.getBody().customerId());
        assertEquals(120l,rewards.getBody().totalPoints());
    }
}