package org.amine.hnms.service;

import org.amine.hnms.DTO.NotificationPerformance;
import org.amine.hnms.domain.*;

import java.time.LocalDateTime;

public interface INotificationService {

    Notification create(Integer hotelId, Notification notification) throws Exception;

    Notification update(Integer id, Notification notification);

    void delete(Integer id);

    Notification getOneById(Integer id) throws Exception;

    Click addClick(Integer notificationId, Click click) throws Exception;

    Impression addImpression(Integer notificationId, Impression impression);

    Conversion addConversion(Integer notificationId, Conversion conversion);

    NotificationPerformance getNotificationPerformance(Integer notificationId, LocalDateTime startDate, LocalDateTime endDate) throws Exception;
}

