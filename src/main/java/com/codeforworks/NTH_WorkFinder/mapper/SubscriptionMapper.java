package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.subscription.SubscriptionResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    SubscriptionResponseDTO toSubscriptionResponseDTO(Subscription subscription);
}