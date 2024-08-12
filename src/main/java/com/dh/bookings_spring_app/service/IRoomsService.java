package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Rooms;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IRoomsService {
    Rooms save(Rooms room);
    Optional<Rooms> findById(Integer id);
    void update(Rooms room);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Rooms> findAll();
}
