package com.codeforworks.NTH_WorkFinder.dto.application;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationStatusUpdateDTO {
    private String status; //Pending, Accepted, Rejected
}