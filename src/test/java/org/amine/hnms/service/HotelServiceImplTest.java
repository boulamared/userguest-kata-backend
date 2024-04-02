package org.amine.hnms.service;

import org.amine.hnms.domain.Hotel;
import org.amine.hnms.repository.HotelRepository;
import org.amine.hnms.service.impl.HotelServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class HotelServiceImplTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateHotel() {
        Hotel hotel = new Hotel();
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel createdHotel = hotelService.create(hotel);

        assertEquals(hotel, createdHotel);
        verify(hotelRepository, times(1)).save(hotel);
    }

    @Test
    public void testGetOneByKey() throws Exception {
        String key = "abc123";
        Hotel hotel = new Hotel();
        when(hotelRepository.findOneByKey(key)).thenReturn(Optional.of(hotel));

        Hotel retrievedHotel = hotelService.getOneByKey(key);

        assertEquals(hotel, retrievedHotel);
    }

    @Test
    public void testGetOneByKeyNotFound() {
        String key = "not_found";
        when(hotelRepository.findOneByKey(key)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> hotelService.getOneByKey(key));
    }

    @Test
    public void testGetOneById() throws Exception {
        int id = 1;
        Hotel hotel = new Hotel();
        when(hotelRepository.findById(id)).thenReturn(Optional.of(hotel));

        Hotel retrievedHotel = hotelService.getOneById(id);

        assertEquals(hotel, retrievedHotel);
    }

    @Test
    public void testGetOneByIdNotFound() {
        int id = 999;
        when(hotelRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> hotelService.getOneById(id));
    }

    @Test
    public void testGetAll() {
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(new Hotel());
        hotels.add(new Hotel());
        when(hotelRepository.findAll()).thenReturn(hotels);

        List<Hotel> retrievedHotels = hotelService.getAll();

        assertEquals(hotels, retrievedHotels);
    }
}
