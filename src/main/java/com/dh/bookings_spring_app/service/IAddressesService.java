package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Addresses;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IAddressesService {
    Addresses save(Addresses address);
    Optional<Addresses> findById(Integer id);
    void update(Addresses address);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Addresses> findAll();
}
