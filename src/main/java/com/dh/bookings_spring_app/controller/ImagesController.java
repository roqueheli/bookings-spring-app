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

import com.dh.bookings_spring_app.entities.Images;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.IImagesService;

@RestController
@RequestMapping("/images")
public class ImagesController {
    @Autowired
    private final IImagesService iImagesService;
    
    public ImagesController(IImagesService iImagesService) {
        this.iImagesService = iImagesService;
    }

    @PostMapping("/save")
    public ResponseEntity<Images> save(@RequestBody Images image) {
        return ResponseEntity.ok(iImagesService.save(image));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Images>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(iImagesService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Images> update(@RequestBody Images image) {
        if (iImagesService.findById(image.getImg_id()).isPresent()) {
            iImagesService.update(image);
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        iImagesService.delete(id);
        return ResponseEntity.ok("Image deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Images>> findAll() {
        return ResponseEntity.ok(iImagesService.findAll());
    }

}
