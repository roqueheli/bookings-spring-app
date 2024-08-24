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

import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.service.IUsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private final IUsersService iUsersService;
    
    public UsersController(IUsersService iUsersService) {
        this.iUsersService = iUsersService;
    }

    @PostMapping("/save")
    public ResponseEntity<Users> save(@RequestBody Users user) {
        return ResponseEntity.ok(iUsersService.save(user));
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Users>> findById(@RequestParam Integer id) {
        return ResponseEntity.ok(iUsersService.findById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Users> update(@RequestBody Users user) {
        if (iUsersService.findById(user.getUser_id()).isPresent()) {
            iUsersService.update(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        iUsersService.delete(id);
        return ResponseEntity.ok("User deleted");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> findAll() {
        return ResponseEntity.ok(iUsersService.findAll());
    }

    @GetMapping("/byowner/{id}")
    public ResponseEntity<List<Users>> findByOwner(@PathVariable("id") Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(iUsersService.findByOwner(id));
    }
}
