package com.dh.bookings_spring_app.controller;

import com.dh.bookings_spring_app.entities.UserPlaces;
import com.dh.bookings_spring_app.service.IUserPlacesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-places")
public class UserPlacesController {

    @Autowired
    private IUserPlacesService userPlacesService;

    // Obtener todas las relaciones entre usuarios y lugares
    @GetMapping
    public ResponseEntity<List<UserPlaces>> getAllUserPlaces() {
        List<UserPlaces> userPlacesList = userPlacesService.findAll();
        return ResponseEntity.ok(userPlacesList);
    }

    // Obtener las relaciones por userId
    @GetMapping("/user/{id}")
    public ResponseEntity<List<UserPlaces>> getUserPlacesByUserId(@PathVariable Integer id) {
        List<UserPlaces> userPlaces = userPlacesService.findByUserId(id);
        return ResponseEntity.ok(userPlaces);
    }

    // Obtener las relaciones por placeId
    @GetMapping("/place/{id}")
    public ResponseEntity<List<UserPlaces>> getUserPlacesByPlaceId(@PathVariable Integer id) {
        List<UserPlaces> userPlaces = userPlacesService.findByPlaceId(id);
        return ResponseEntity.ok(userPlaces);
    }

    // Crear una nueva relación entre un usuario y un lugar
    @PostMapping
    public ResponseEntity<UserPlaces> createUserPlace(@RequestBody UserPlaces userPlace) {
        UserPlaces newUserPlace = userPlacesService.save(userPlace);
        return ResponseEntity.ok(newUserPlace);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserPlaces>> findAll() {
        return ResponseEntity.ok(userPlacesService.findAll());
    }

    // // Eliminar una relación entre un usuario y un lugar
    // @DeleteMapping("/{userId}/{placeId}")
    // public ResponseEntity<Void> deleteUserPlace(@PathVariable Integer userId, @PathVariable Integer placeId) {
    //     userPlacesService.delete(userId, placeId);
    //     return ResponseEntity.noContent().build();
    // }
}
