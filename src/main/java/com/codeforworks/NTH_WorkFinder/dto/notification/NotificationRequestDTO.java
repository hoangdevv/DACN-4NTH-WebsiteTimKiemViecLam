package com.codeforworks.NTH_WorkFinder.dto.notification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDTO {
    private Long userId;
    private String title;
    private String message;
}