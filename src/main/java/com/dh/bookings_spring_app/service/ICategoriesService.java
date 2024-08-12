package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Categories;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface ICategoriesService {
    Categories save(Categories category);
    Optional<Categories> findById(Integer id);
    void update(Categories category);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Categories> findAll();
}
