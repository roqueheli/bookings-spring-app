package com.dh.bookings_spring_app.service.impl;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.PlaceRoom;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.PlaceRoomRepository;
import com.dh.bookings_spring_app.service.IPlaceRoomService;

public class PlaceRoomServiceImpl implements IPlaceRoomService {
    private PlaceRoomRepository placeRoomRepository;

    public PlaceRoomServiceImpl(PlaceRoomRepository placeRoomRepository) {
        this.placeRoomRepository = placeRoomRepository;
    }

    @Override
    public PlaceRoom save(PlaceRoom placeRoom) {
        return placeRoomRepository.save(placeRoom);
    }

    @Override
    public Optional<PlaceRoom> findById(Integer id) {
        return placeRoomRepository.findById(id);
    }

    @Override
    public void update(PlaceRoom placeRoom) {
        placeRoomRepository.save(placeRoom);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (placeRoomRepository.findById(id).isPresent()) {
            placeRoomRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("place not found");
        }
    }

    @Override
    public List<PlaceRoom> findAll() {
        return placeRoomRepository.findAll();
    }
}
