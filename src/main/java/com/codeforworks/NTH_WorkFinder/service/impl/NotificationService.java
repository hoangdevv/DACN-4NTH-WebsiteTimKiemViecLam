package com.codeforworks.NTH_WorkFinder.service.impl;

import com.codeforworks.NTH_WorkFinder.dto.notification.NotificationRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.notification.NotificationResponseDTO;
import com.codeforworks.NTH_WorkFinder.mapper.NotificationMapper;
import com.codeforworks.NTH_WorkFinder.model.Notification;
import com.codeforworks.NTH_WorkFinder.model.User;
import com.codeforworks.NTH_WorkFinder.repository.NotificationRepository;
import com.codeforworks.NTH_WorkFinder.repository.UserRepository;
import com.codeforworks.NTH_WorkFinder.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public NotificationResponseDTO createNotification(NotificationRequestDTO notificationRequestDTO) {
        User user = userRepository.findById(notificationRequestDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Notification notification = NotificationMapper.INSTANCE.toNotificationEntity(notificationRequestDTO);
        notification.setUser(user);
        notification.setIsRead(false);

        Notification savedNotification = notificationRepository.save(notification);
        return NotificationMapper.INSTANCE.toNotificationResponseDTO(savedNotification);
    }

    @Override
    public NotificationResponseDTO getNotificationById(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        return NotificationMapper.INSTANCE.toNotificationResponseDTO(notification);
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserId(userId);
        return notifications.stream()
                .map(NotificationMapper.INSTANCE::toNotificationResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public NotificationResponseDTO markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        Notification updatedNotification = notificationRepository.save(notification);
        return NotificationMapper.INSTANCE.toNotificationResponseDTO(updatedNotification);
    }

    @Override
    public void deleteNotification(Long id) {
        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException("Notification not found");
        }
        notificationRepository.deleteById(id);
    }
}
