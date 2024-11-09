package com.codeforworks.NTH_WorkFinder.dto.notification;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotificationResponseDTO {
    private Long id;
    private Long userId;
    private String title;
    private String message;
    private Boolean isRead;
    private Date createdDate;
}
