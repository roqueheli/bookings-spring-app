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
import org.springframework.web.bind.annotation.RestController;

import com.dh.bookings_spring_app.entities.Places;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.IPlacesService;

@RestController
@RequestMapping("/places")
public class PlacesController {
    @Autowired
    private final IPlacesService iPlacesService;
    
    public PlacesController(IPlacesService iPlacesService) {
        this.iPlacesService = iPlacesService;
    }

    @PostMapping("/save")
    public ResponseEntity<Places> save(@RequestBody Places place) {
        return ResponseEntity.ok(iPlacesService.save(place));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Places>> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(iPlacesService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Places> update(@RequestBody Places place) {
        if (iPlacesService.findById(place.getPlace_id()).isPresent()) {
            iPlacesService.update(place);
            return ResponseEntity.ok(place);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) throws ResourceNotFoundException {
        iPlacesService.delete(id);
        return ResponseEntity.ok("Place deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Places>> findAll() {
        return ResponseEntity.ok(iPlacesService.findAll());
    }

    @GetMapping("/random")
    public ResponseEntity<List<Places>> getRandomProducts() {
        return ResponseEntity.ok(iPlacesService.getRandomProducts());
    }

    @GetMapping("/byowner/{id}")
    public ResponseEntity<Optional<List<Places>>> byowner(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(iPlacesService.findPlacesByOwner(id));
    }
}
