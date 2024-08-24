package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.Addresses;
import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.AddressesRepository;
import com.dh.bookings_spring_app.repository.UsersRepository;
import com.dh.bookings_spring_app.service.IUsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements IUsersService {
    private UsersRepository usersRepository;
    private AddressesRepository addressesRepository;

    public UsersServiceImpl(UsersRepository usersRepository, AddressesRepository addressesRepository) {
        this.usersRepository = usersRepository;
        this.addressesRepository = addressesRepository;
    }

    @Override
    public Users save(Users user) {
        if (usersRepository.findByEmail(user.getEmail().trim()).isPresent()) {
            throw new ResourceNotFoundException("Place already exists");
        }

        Addresses address = user.getAddress();

        if (address.getAddress_id() == null) {
            address = addressesRepository.save(address);
        } else {
            try {
                address = addressesRepository.findById(address.getAddress_id())
                        .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }

        user.setAddress(address);
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

    @Override
    public List<Users> findByOwner(Integer id) {
        return usersRepository.findByOwner(id);
    }
}
