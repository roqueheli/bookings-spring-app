package com.dh.bookings_spring_app.service.impl;

import java.util.List;
import java.util.Optional;

import com.dh.bookings_spring_app.entities.Addresses;
import com.dh.bookings_spring_app.exception.ResourceNotFoundException;
import com.dh.bookings_spring_app.repository.AddressesRepository;
import com.dh.bookings_spring_app.service.IAddressesService;

public class AddressesServiceImpl implements IAddressesService {
    private AddressesRepository addressesRepository;

    public AddressesServiceImpl(AddressesRepository addressesRepository){
        this.addressesRepository = addressesRepository;
    }

    @Override
    public Addresses save(Addresses address) {
        return addressesRepository.save(address);
    }

    @Override
    public Optional<Addresses> findById(Integer id) {
        return addressesRepository.findById(id);
    }

    @Override
    public void update(Addresses address) {
        addressesRepository.save(address);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        if (addressesRepository.findById(id).isPresent()) {
            addressesRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("address not found");
        }
    }

    @Override
    public List<Addresses> findAll() {
        return addressesRepository.findAll();
    }
   
    
}
