package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Reservations;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IReservationsService {
    Reservations save(Reservations reservation);
    Optional<Reservations> findById(Integer id);
    void update(Reservations reservation);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Reservations> findAll();
}
