package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;

import java.util.List;

public interface ISubscriptionService {

    SubscriptionResponseDTO createSubscription(SubscriptionRequestDTO subscriptionRequestDTO);

    SubscriptionResponseDTO getSubscriptionById(Long id);

    List<SubscriptionResponseDTO> getAllSubscriptions();

    SubscriptionResponseDTO renewSubscription(Long id, Integer extraDays);

    void cancelSubscription(Long id);
}
