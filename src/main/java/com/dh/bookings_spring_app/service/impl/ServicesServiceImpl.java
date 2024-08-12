package com.dh.bookings_spring_app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dh.bookings_spring_app.entities.Services;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.ServicesRepository;
import com.dh.bookings_spring_app.service.IServicesService;

@Service
public class ServicesServiceImpl implements IServicesService {
    private ServicesRepository servicesRepository;

    public ServicesServiceImpl(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    @Override
    public Services save(Services service) {
        return servicesRepository.save(service);
    }

    @Override
    public Optional<Services> findById(Integer id) {
        return servicesRepository.findById(id);
    }

    @Override
    public void update(Services service) {
        servicesRepository.save(service);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (servicesRepository.findById(id).isPresent()) {
            servicesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("service not found");
        }
    }

    @Override
    public List<Services> findAll() {
        return servicesRepository.findAll();
    }}
