package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Images;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;


public interface IImagesService {
    Images save(Images image);
    Optional<Images> findById(Integer id);
    void update(Images image);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Images> findAll();
}
