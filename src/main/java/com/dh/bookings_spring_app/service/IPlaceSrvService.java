package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.PlaceService;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IPlaceSrvService {
    PlaceService save(PlaceService placeSrv);
    Optional<PlaceService> findById(Integer id);
    void update(PlaceService placeSrv);
    void delete(Integer id) throws ResourceNotFoundException;
    List<PlaceService> findAll();
}
