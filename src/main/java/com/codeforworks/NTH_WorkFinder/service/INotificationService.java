package com.codeforworks.NTH_WorkFinder.service;

import com.codeforworks.NTH_WorkFinder.dto.notification.NotificationRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.notification.NotificationResponseDTO;

import java.util.List;

public interface INotificationService {

    NotificationResponseDTO createNotification(NotificationRequestDTO notificationRequestDTO);

    NotificationResponseDTO getNotificationById(Long id);

    List<NotificationResponseDTO> getNotificationsByUserId(Long userId);

    NotificationResponseDTO markAsRead(Long id);

    void deleteNotification(Long id);
}
