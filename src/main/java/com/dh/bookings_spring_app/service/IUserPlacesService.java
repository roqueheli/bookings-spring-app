package com.dh.bookings_spring_app.service;

import com.dh.bookings_spring_app.entities.UserPlaces;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserPlacesService {
    UserPlaces save(UserPlaces userPlace);
    Optional<UserPlaces> findById(Integer id);
    List<UserPlaces> findByUserId(Integer id);
    List<UserPlaces> findByPlaceId(Integer id);
    void update(UserPlaces userPlace);
    void delete(Integer id) throws ResourceNotFoundException;
    List<UserPlaces> findAll();
}
