package org.amine.hnms.service;

import org.amine.hnms.domain.Hotel;

import java.util.List;

public interface IHotelService {

    public Hotel create(Hotel hotel);

    public Hotel getOneByKey(String key) throws Exception;

    public Hotel getOneById(Integer id) throws Exception;

    public List<Hotel> getAll();
}
