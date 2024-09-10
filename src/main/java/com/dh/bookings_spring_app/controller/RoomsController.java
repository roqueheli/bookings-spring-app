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

import com.dh.bookings_spring_app.entities.Rooms;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.IRoomsService;

@RestController
@RequestMapping("/rooms")
public class RoomsController {
    @Autowired
    private final IRoomsService iRoomsService;
    
    public RoomsController(IRoomsService iRoomsService) {
        this.iRoomsService = iRoomsService;
    }

    @PostMapping("/save")
    public ResponseEntity<Rooms> save(@RequestBody Rooms rooms) {
        return ResponseEntity.ok(iRoomsService.save(rooms));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Rooms>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(iRoomsService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Rooms> update(@RequestBody Rooms rooms) {
        if (iRoomsService.findById(rooms.getRoom_id()).isPresent()) {
            iRoomsService.update(rooms);
            return ResponseEntity.ok(rooms);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        iRoomsService.delete(id);
        return ResponseEntity.ok("Room deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Rooms>> findAll() {
        return ResponseEntity.ok(iRoomsService.findAll());
    }

}
