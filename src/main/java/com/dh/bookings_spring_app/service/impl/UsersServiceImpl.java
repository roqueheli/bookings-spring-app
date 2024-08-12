package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.UsersRepository;
import com.dh.bookings_spring_app.service.IUsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements IUsersService {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public Optional<Users> findById(Integer id) {
        return usersRepository.findById(id);
    }

    @Override
    public void update(Users user) {
        usersRepository.save(user);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("place not found");
        }
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }
}
