package com.codeforworks.NTH_WorkFinder.dto.subscription;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SubscriptionResponseDTO {
    private Long id;
    private Long employerId;
    private Long packageId;
    private Date startDate;
    private Date endDate;
    private Boolean isActive;
}