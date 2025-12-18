package com.example.rewards;

import com.example.rewards.controller.RewardsAPIController;
import com.example.rewards.model.CustomerReward;
import com.example.rewards.model.MonthlyReward;
import com.example.rewards.service.RewardsAPIService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RewardsAPIController.class)
class RewardsIntegrationTest {

    @MockBean
    private RewardsAPIService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetRewards_success() throws Exception {

        MonthlyReward mRewards = new MonthlyReward("SEPTEMBER", 120);
        List<MonthlyReward> monthlyRewards = new ArrayList<>();
        monthlyRewards.add(mRewards);
        CustomerReward customerRewards = new CustomerReward(1l, monthlyRewards, 120);
        when(service.getRewardsForCustomer(1L)).thenReturn(customerRewards);

        mockMvc.perform(get("/api/rewards/{customerId}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value(1))
                .andExpect(jsonPath("$.totalPoints").value(120));

        verify(service, times(1)).getRewardsForCustomer(1L);
    }
}