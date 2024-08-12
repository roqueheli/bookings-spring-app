package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.Categories;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.CategoriesRepository;
import com.dh.bookings_spring_app.service.ICategoriesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements ICategoriesService {
    private CategoriesRepository categoriesRepository;

    public CategoriesServiceImpl(CategoriesRepository categoriesRepository) {
        this.categoriesRepository = categoriesRepository;
    }

    @Override
    public Categories save(Categories category) {
        return categoriesRepository.save(category);
    }

    @Override
    public Optional<Categories> findById(Integer id) {
        return categoriesRepository.findById(id);
    }

    @Override
    public void update(Categories category) {
        categoriesRepository.save(category);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (categoriesRepository.findById(id).isPresent()) {
            categoriesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("category not found");
        }
    }

    @Override
    public List<Categories> findAll() {
        return categoriesRepository.findAll();
    }
}
