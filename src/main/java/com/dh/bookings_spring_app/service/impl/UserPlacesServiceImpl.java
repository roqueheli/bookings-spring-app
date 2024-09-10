package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.UserPlaces;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.UserPlacesRepository;
import com.dh.bookings_spring_app.service.IUserPlacesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPlacesServiceImpl implements IUserPlacesService {

    @Autowired
    private UserPlacesRepository userPlacesRepository;

    @Override
    public UserPlaces save(UserPlaces userPlace) {
        return userPlacesRepository.save(userPlace);
    }

    @Override
    public Optional<UserPlaces> findById(Integer id) {
        return userPlacesRepository.findById(id);
    }

    @Override
    public List<UserPlaces> findByUserId(Integer id) {
        return userPlacesRepository.findByUserUserId(id);
    }

    @Override
    public List<UserPlaces> findByPlaceId(Integer placeId) {
        return userPlacesRepository.findByPlacePlaceId(placeId);
    }

    @Override
    public void update(UserPlaces userPlace) {
        userPlacesRepository.save(userPlace);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        Optional<UserPlaces> existing = userPlacesRepository.findById(id);
        if (existing.isPresent()) {
            userPlacesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("UserPlace not found with id " + id);
        }
    }

    @Override
    public List<UserPlaces> findAll() {
        return userPlacesRepository.findAll();
    }
}
