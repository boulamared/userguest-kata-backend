package org.amine.hnms.controller;

import org.amine.hnms.domain.Hotel;
import org.amine.hnms.domain.Notification;
import org.amine.hnms.service.IHotelService;
import org.amine.hnms.service.INotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HotelControllerTest {

    @Mock
    private IHotelService hotelService;

    @Mock
    private INotificationService notificationService;

    @InjectMocks
    private HotelController hotelController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateHotel() {
        Hotel hotel = new Hotel();
        hotel.setName("Hotel ABC");
        when(hotelService.create(hotel)).thenReturn(hotel);

        ResponseEntity<Hotel> response = hotelController.create(hotel);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(hotel, response.getBody());
    }

    @Test
    public void testGetAllHotels() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel());
        hotels.add(new Hotel());
        when(hotelService.getAll()).thenReturn(hotels);

        ResponseEntity<List<Hotel>> response = hotelController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(hotels, response.getBody());
    }

    @Test
    public void testCreateNotification() throws Exception {
        int hotelId = 1;
        Notification notification = new Notification();
        when(notificationService.create(hotelId, notification)).thenReturn(notification);

        ResponseEntity<Notification> response = hotelController.createNotification(hotelId, notification);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(notification, response.getBody());
    }

    @Test
    public void testGetAllNotificationsByHotelId() throws Exception {
        int hotelId = 1;
        List<Notification> notifications = new ArrayList<>();
        notifications.add(new Notification());
        notifications.add(new Notification());
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        hotel.setNotifications(notifications);
        when(hotelService.getOneById(hotelId)).thenReturn(hotel);

        ResponseEntity<List<Notification>> response = hotelController.getAllByHotelId(hotelId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(notifications, response.getBody());
    }
}
