package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Services;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IServicesService {
    Services save(Services service);
    Optional<Services> findById(Integer id);
    void update(Services service);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Services> findAll();
}
