package com.codeforworks.NTH_WorkFinder.mapper;

import com.codeforworks.NTH_WorkFinder.dto.notification.NotificationRequestDTO;
import com.codeforworks.NTH_WorkFinder.dto.notification.NotificationResponseDTO;
import com.codeforworks.NTH_WorkFinder.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    Notification toNotificationEntity(NotificationRequestDTO dto);

    NotificationResponseDTO toNotificationResponseDTO(Notification notification);
}