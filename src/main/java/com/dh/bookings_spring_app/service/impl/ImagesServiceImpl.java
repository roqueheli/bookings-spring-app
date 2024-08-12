package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.Images;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.ImagesRepository;
import com.dh.bookings_spring_app.service.IImagesService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagesServiceImpl implements IImagesService {
    private ImagesRepository inmImagesRepository;

    public ImagesServiceImpl(ImagesRepository inmImagesRepository) {
        this.inmImagesRepository = inmImagesRepository;
    }

    @Override
    public Images save(Images image) {
        return inmImagesRepository.save(image);
    }

    @Override
    public Optional<Images> findById(Integer id) {
        return inmImagesRepository.findById(id);
    }

    @Override
    public void update(Images image) {
        inmImagesRepository.save(image);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (inmImagesRepository.findById(id).isPresent()) {
            inmImagesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("image not found");
        }
    }

    @Override
    public List<Images> findAll() {
        return inmImagesRepository.findAll();
    }
}
