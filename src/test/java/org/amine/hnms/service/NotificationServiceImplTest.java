package org.amine.hnms.service;

import org.amine.hnms.DTO.NotificationPerformance;
import org.amine.hnms.domain.*;
import org.amine.hnms.exception.BusinessObjectNotFoundException;
import org.amine.hnms.repository.*;
import org.amine.hnms.service.impl.NotificationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationServiceImplTest {
    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private IHotelService hotelService;

    @Mock
    private ClickRepository clickRepository;

    @Mock
    private ImpressionRepository impressionRepository;

    @Mock
    private ConversionRepository conversionRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateNotification() throws Exception {
        int hotelId = 1;
        Notification notification = new Notification();
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        when(hotelService.getOneById(hotelId)).thenReturn(hotel);
        when(notificationRepository.save(notification)).thenReturn(notification);

        Notification createdNotification = notificationService.create(hotelId, notification);

        assertEquals(notification, createdNotification);
        assertEquals(hotel, notification.getHotel());
        verify(hotelService, times(1)).getOneById(hotelId);
        verify(notificationRepository, times(1)).save(notification);
    }

    @Test
    public void testCreateNotification_HotelNotFound() throws Exception {
        int hotelId = 1;
        Notification notification = new Notification();
        when(hotelService.getOneById(hotelId)).thenThrow(new BusinessObjectNotFoundException("Hotel not found with id: " + hotelId));

        assertThrows(BusinessObjectNotFoundException.class, () -> {
            notificationService.create(hotelId, notification);
        });
        verify(hotelService, times(1)).getOneById(hotelId);
        verifyNoInteractions(notificationRepository);
    }

    @Test
    public void testGetOneById_NotificationFound() throws Exception {
        int notificationId = 1;
        Notification notification = new Notification();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));

        Notification retrievedNotification = notificationService.getOneById(notificationId);
        assertEquals(notification, retrievedNotification);
    }

    @Test
    public void testGetOneById_NotificationNotFound() {
        int notificationId = 1;
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.empty());
        assertThrows(BusinessObjectNotFoundException.class, () -> {
            notificationService.getOneById(notificationId);
        });
    }

    @Test
    public void testGetNotificationPerformance() throws Exception {
        int notificationId = 1;
        LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2024, 1, 2, 0, 0);
        Notification notification = new Notification();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(clickRepository.findByNotificationAndCreatedAtBetween(notification, startDate, endDate)).thenReturn(new ArrayList<>());
        when(impressionRepository.findByNotificationAndCreatedAtBetween(notification, startDate, endDate)).thenReturn(new ArrayList<>());
        when(conversionRepository.findByNotificationAndCreatedAtBetween(notification, startDate, endDate)).thenReturn(new ArrayList<>());

        NotificationPerformance performance = notificationService.getNotificationPerformance(notificationId, startDate, endDate);

        assertEquals(0, performance.getClicks());
        assertEquals(0, performance.getImpressions());
        assertEquals(0, performance.getConversions());
        assertEquals(0, performance.getCTR());
        assertEquals(0, performance.getCVR());
    }

    @Test
    public void testAddClick() throws Exception {
        int notificationId = 1;
        Notification notification = new Notification();
        Click click = new Click();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(clickRepository.save(click)).thenReturn(click);

        Click result = notificationService.addClick(notificationId, click);

        assertEquals(click, result);
        assertEquals(notification, click.getNotification());
        assertNotNull(click.getCreatedAt());
        verify(notificationRepository, times(1)).findById(notificationId);
        verify(clickRepository, times(1)).save(click);
    }

    @Test
    public void testAddImpression() {
        int notificationId = 1;
        Notification notification = new Notification();
        Impression impression = new Impression();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(impressionRepository.save(impression)).thenReturn(impression);

        Impression result = notificationService.addImpression(notificationId, impression);

        assertEquals(impression, result);
        assertEquals(notification, impression.getNotification());
        assertNotNull(impression.getCreatedAt());
        verify(notificationRepository, times(1)).findById(notificationId);
        verify(impressionRepository, times(1)).save(impression);
    }

    @Test
    public void testAddConversion() {
        int notificationId = 1;
        Notification notification = new Notification();
        Conversion conversion = new Conversion();
        when(notificationRepository.findById(notificationId)).thenReturn(Optional.of(notification));
        when(conversionRepository.save(conversion)).thenReturn(conversion);

        Conversion result = notificationService.addConversion(notificationId, conversion);

        assertEquals(conversion, result);
        assertEquals(notification, conversion.getNotification());
        assertNotNull(conversion.getCreatedAt());
        verify(notificationRepository, times(1)).findById(notificationId);
        verify(conversionRepository, times(1)).save(conversion);
    }
}
