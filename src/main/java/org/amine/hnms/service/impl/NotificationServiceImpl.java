package org.amine.hnms.service.impl;

import org.amine.hnms.DTO.NotificationPerformance;
import org.amine.hnms.domain.*;
import org.amine.hnms.exception.BusinessObjectNotFoundException;
import org.amine.hnms.repository.*;
import org.amine.hnms.service.IHotelService;
import org.amine.hnms.service.INotificationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService {

    private final NotificationRepository notificationRepository;
    private final IHotelService hotelService;
    private final ClickRepository clickRepository;
    private final ImpressionRepository impressionRepository;
    private final ConversionRepository conversionRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, IHotelService hotelService, ClickRepository clickRepository, ImpressionRepository impressionRepository, ConversionRepository conversionRepository) {
        this.notificationRepository = notificationRepository;
        this.hotelService = hotelService;
        this.clickRepository = clickRepository;
        this.impressionRepository = impressionRepository;
        this.conversionRepository = conversionRepository;
    }


    @Override
    public Notification create(Integer hotelId,Notification notification) throws Exception {
        Hotel hotel = this.hotelService.getOneById(hotelId);
        notification.setHotel(hotel);
        notification.setCreatedAt(LocalDateTime.now());
        return this.notificationRepository.save(notification);
    }

    @Override
    public Notification update(Integer id, Notification notification) {
        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() -> new BusinessObjectNotFoundException("Notification not found with id: " + id));
        existingNotification.setMessage(notification.getMessage());
        return notificationRepository.save(existingNotification);
    }

    @Override
    public void delete(Integer id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new BusinessObjectNotFoundException("Notification not found with id: " + id));
        notificationRepository.delete(notification);
    }

    @Override
    public Notification getOneById(Integer id) throws Exception {
        return this.notificationRepository.findById(id).orElseThrow(() -> new BusinessObjectNotFoundException("Notification Not found with Id:" + id));
    }

    @Override
    public Click addClick(Integer notificationId, Click click) throws Exception {
        Notification notification = this.getOneById(notificationId);
        click.setNotification(notification);
        click.setCreatedAt(LocalDateTime.now());
        return this.clickRepository.save(click);
    }

    @Override
    public Impression addImpression(Integer notificationId, Impression impression) {
        Notification notification = this.notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found "));
        impression.setNotification(notification);
        impression.setCreatedAt(LocalDateTime.now());
        return this.impressionRepository.save(impression);
    }

    @Override
    public Conversion addConversion(Integer notificationId, Conversion conversion) {
        Notification notification = this.notificationRepository.findById(notificationId).orElseThrow(() -> new RuntimeException("Notification not found "));
        conversion.setNotification(notification);
        conversion.setCreatedAt(LocalDateTime.now());
        return this.conversionRepository.save(conversion);
    }

    @Override
    public NotificationPerformance getNotificationPerformance(Integer notificationId, LocalDateTime startDate, LocalDateTime endDate) throws Exception {
        Notification notification = this.getOneById(notificationId);
        List<Click> clicks = this.clickRepository.findByNotificationAndCreatedAtBetween(notification,startDate, endDate);
        List<Impression> impressions = this.impressionRepository.findByNotificationAndCreatedAtBetween(notification,startDate, endDate);
        List<Conversion> conversions = this.conversionRepository.findByNotificationAndCreatedAtBetween(notification,startDate, endDate);
        Integer ctr = impressions.isEmpty()?0:(clicks.size()/impressions.size());
        Integer cvr = impressions.isEmpty()?0:conversions.size()/impressions.size();
        return NotificationPerformance.builder()
                .clicks(clicks.size())
                .impressions(impressions.size())
                .conversions(conversions.size())
                .CTR(ctr)
                .CVR(cvr)
                .build();
    }
}
