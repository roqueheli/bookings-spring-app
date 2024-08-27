package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Places;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IPlacesService {
    Places save(Places place) throws ResourceNotFoundException;
    Optional<Places> findById(Integer id);
    void update(Places place);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Places> findAll();
    List<Places> getRandomProducts();
    Optional<List<Places>> findPlacesByOwner(Integer id);
}
