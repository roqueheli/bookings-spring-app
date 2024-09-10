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

import com.dh.bookings_spring_app.entities.Services;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.IServicesService;

@RestController
@RequestMapping("/services")
public class ServicesController {
    @Autowired
    private final IServicesService iServicesService;
    
    public ServicesController(IServicesService iServicesService) {
        this.iServicesService = iServicesService;
    }

    @PostMapping("/save")
    public ResponseEntity<Services> save(@RequestBody Services service) {
        return ResponseEntity.ok(iServicesService.save(service));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Services>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(iServicesService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Services> update(@RequestBody Services service) {
        if (iServicesService.findById(service.getService_id()).isPresent()) {
            iServicesService.update(service);
            return ResponseEntity.ok(service);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        iServicesService.delete(id);
        return ResponseEntity.ok("Service deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Services>> findAll() {
        return ResponseEntity.ok(iServicesService.findAll());
    }
}
