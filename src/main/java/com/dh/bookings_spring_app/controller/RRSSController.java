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

import com.dh.bookings_spring_app.entities.RRSS;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.IRRSSService;

@RestController
@RequestMapping("/rrss")
public class RRSSController {
    @Autowired 
    private final IRRSSService irrssService;
    
    public RRSSController(IRRSSService irrssService) {
        this.irrssService = irrssService;
    }

    @PostMapping("/save")
    public ResponseEntity<RRSS> save(@RequestBody RRSS rrss) {
        return ResponseEntity.ok(irrssService.save(rrss));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<RRSS>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(irrssService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<RRSS> update(@RequestBody RRSS rrss) {
        if (irrssService.findById(rrss.getRrssId()).isPresent()) {
            irrssService.update(rrss);
            return ResponseEntity.ok(rrss);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        irrssService.delete(id);
        return ResponseEntity.ok("Category deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<RRSS>> findAll() {
        return ResponseEntity.ok(irrssService.findAll());
    }

}
