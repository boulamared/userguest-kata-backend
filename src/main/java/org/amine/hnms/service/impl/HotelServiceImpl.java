package org.amine.hnms.service.impl;

import org.amine.hnms.domain.Hotel;
import org.amine.hnms.exception.BusinessObjectNotFoundException;
import org.amine.hnms.repository.HotelRepository;
import org.amine.hnms.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements IHotelService {

    private final HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Override
    public Hotel create(Hotel hotel) {
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getOneByKey(String key) {
        return this.hotelRepository.findOneByKey(key).orElseThrow(() -> new BusinessObjectNotFoundException("Hotel not found with key: " + key));
    }

    @Override
    public Hotel getOneById(Integer id) {
        return this.hotelRepository.findById(id).orElseThrow(() -> new BusinessObjectNotFoundException("Hotel not found with key: " + id));
    }

    @Override
    public List<Hotel> getAll() {
        return this.hotelRepository.findAll();
    }
}
