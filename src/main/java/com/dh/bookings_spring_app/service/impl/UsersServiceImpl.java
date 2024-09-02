package com.dh.bookings_spring_app.service.impl;

import com.dh.bookings_spring_app.entities.Addresses;
import com.dh.bookings_spring_app.entities.Images;
import com.dh.bookings_spring_app.entities.UserPlaces;
import com.dh.bookings_spring_app.entities.Users;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.AddressesRepository;
import com.dh.bookings_spring_app.repository.UserPlacesRepository;
import com.dh.bookings_spring_app.repository.UsersRepository;
import com.dh.bookings_spring_app.service.IUsersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsersServiceImpl implements IUsersService {
    private UsersRepository usersRepository;
    private AddressesRepository addressesRepository;
    private UserPlacesRepository userPlacesRepository;

    public UsersServiceImpl(UsersRepository usersRepository, AddressesRepository addressesRepository, UserPlacesRepository userPlacesRepository) {
        this.usersRepository = usersRepository;
        this.addressesRepository = addressesRepository;
        this.userPlacesRepository = userPlacesRepository;
    }

    @Override
    public Users save(Users user) {
        if (usersRepository.findByEmail(user.getEmail().trim()).isPresent()) {
            throw new ResourceNotFoundException("User already exists");
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
        Users existingUser = usersRepository.findByEmail(user.getEmail().trim())
        .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existingUser.getPassword());
        }

        Addresses address = user.getAddress();
        try {
            address = addressesRepository.findById(address.getAddress_id())
                    .orElseThrow(() -> new ResourceNotFoundException("Address not found"));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        addressesRepository.save(user.getAddress());

        // Gestionar los UserPlaces
        Set<UserPlaces> userPlaces = user.getUserPlaces();
        if (userPlaces != null && !userPlaces.isEmpty()) {
            userPlacesRepository.deleteAll(existingUser.getUserPlaces());
            for (UserPlaces userPlace : userPlaces) {
                userPlace.setUser(user);
                userPlacesRepository.save(userPlace);
            }
        }
        
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
