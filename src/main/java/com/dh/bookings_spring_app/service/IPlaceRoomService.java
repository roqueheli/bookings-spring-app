package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.PlaceRoom;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IPlaceRoomService {
    PlaceRoom save(PlaceRoom placeRoom);
    Optional<PlaceRoom> findById(Integer id);
    void update(PlaceRoom placeRoom);
    void delete(Integer id) throws ResourceNotFoundException;
    List<PlaceRoom> findAll();
}
