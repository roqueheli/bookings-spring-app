package com.dh.bookings_spring_app.service;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;

public interface IUsersService {
    Users save(Users user);
    Optional<Users> findById(Integer id);
    List<Users> findByOwner(Integer id);
    void update(Users user);
    void delete(Integer id) throws ResourceNotFoundException;
    List<Users> findAll();
}
