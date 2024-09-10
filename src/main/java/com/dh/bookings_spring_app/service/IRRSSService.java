package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.RRSS;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;


public interface IRRSSService {
    RRSS save(RRSS rrss);
    Optional<RRSS> findById(Integer id);
    void update(RRSS rrss);
    void delete(Integer id) throws ResourceNotFoundException;
    List<RRSS> findAll();
}
