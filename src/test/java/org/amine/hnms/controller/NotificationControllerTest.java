package org.amine.hnms.controller;

import org.amine.hnms.DTO.NotificationPerformance;
import org.amine.hnms.domain.Click;
import org.amine.hnms.domain.Conversion;
import org.amine.hnms.domain.Impression;
import org.amine.hnms.domain.Notification;
import org.amine.hnms.service.INotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotificationControllerTest {

    @Mock
    private INotificationService notificationService;

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetNotificationById() throws Exception {
        int notificationId = 1;
        Notification notification = new Notification();
        when(notificationService.getOneById(notificationId)).thenReturn(notification);

        ResponseEntity<Notification> response = notificationController.getOneById(notificationId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notification, response.getBody());
    }

    @Test
    public void testGetNotificationPerformanceById() throws Exception {
        int notificationId = 1;
        LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 1, 2, 0, 0);
        NotificationPerformance performance = new NotificationPerformance();
        when(notificationService.getNotificationPerformance(notificationId, startDate, endDate)).thenReturn(performance);

        ResponseEntity<NotificationPerformance> response = notificationController.getNotificationPerformanceById(notificationId, startDate, endDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(performance, response.getBody());
    }

    @Test
    public void testAddClick() throws Exception {
        int notificationId = 1;
        Click click = new Click();
        when(notificationService.addClick(notificationId, click)).thenReturn(click);

        ResponseEntity<Click> response = notificationController.addClick(notificationId, click);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(click, response.getBody());
    }

    @Test
    public void testAddImpression() {
        int notificationId = 1;
        Impression impression = new Impression();
        when(notificationService.addImpression(notificationId, impression)).thenReturn(impression);

        ResponseEntity<Impression> response = notificationController.addImpression(notificationId, impression);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(impression, response.getBody());
    }

    @Test
    public void testAddConversion() {
        int notificationId = 1;
        Conversion conversion = new Conversion();
        when(notificationService.addConversion(notificationId, conversion)).thenReturn(conversion);

        ResponseEntity<Conversion> response = notificationController.addConversion(notificationId, conversion);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(conversion, response.getBody());
    }
}
