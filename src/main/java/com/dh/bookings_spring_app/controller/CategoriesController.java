package com.dh.bookings_spring_app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dh.bookings_spring_app.entities.Categories;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.ICategoriesService;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired 
    private final ICategoriesService iCategoriesService;
    
    public CategoriesController(ICategoriesService iCategoriesService) {
        this.iCategoriesService = iCategoriesService;
    }

    @PostMapping("/save")
    public ResponseEntity<Categories> save(@RequestBody Categories category) {
        return ResponseEntity.ok(iCategoriesService.save(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Categories>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(iCategoriesService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Categories> update(@RequestBody Categories category) {
        if (iCategoriesService.findById(category.getCategory_id()).isPresent()) {
            iCategoriesService.update(category);
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        iCategoriesService.delete(id);
        return ResponseEntity.ok("Category deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Categories>> findAll() {
        return ResponseEntity.ok(iCategoriesService.findAll());
    }

}
