package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.RRSS;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.RRSSRepository;
import com.dh.bookings_spring_app.service.IRRSSService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RRSSServiceImpl implements IRRSSService {
    private RRSSRepository rrssRepository;

    public RRSSServiceImpl(RRSSRepository rrssRepository) {
        this.rrssRepository = rrssRepository;
    }

    @Override
    public RRSS save(RRSS rrss) {
        return rrssRepository.save(rrss);
    }

    @Override
    public Optional<RRSS> findById(Integer id) {
        return rrssRepository.findById(id);
    }

    @Override
    public void update(RRSS rrss) {
        rrssRepository.save(rrss);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (rrssRepository.findById(id).isPresent()) {
            rrssRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("category not found");
        }
    }

    @Override
    public List<RRSS> findAll() {
        return rrssRepository.findAll();
    }
}
