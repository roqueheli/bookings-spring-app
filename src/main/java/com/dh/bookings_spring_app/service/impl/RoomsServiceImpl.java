package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.Rooms;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.RoomsRepository;
import com.dh.bookings_spring_app.service.IRoomsService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomsServiceImpl implements IRoomsService {
    private RoomsRepository roomsRepository;

    public RoomsServiceImpl(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public Rooms save(Rooms room) {
        return roomsRepository.save(room);
    }

    @Override
    public Optional<Rooms> findById(Integer id) {
        return roomsRepository.findById(id);
    }

    @Override
    public void update(Rooms room) {
        roomsRepository.save(room);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (roomsRepository.findById(id).isPresent()) {
            roomsRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("image not found");
        }
    }

    @Override
    public List<Rooms> findAll() {
        return roomsRepository.findAll();
    }
}
