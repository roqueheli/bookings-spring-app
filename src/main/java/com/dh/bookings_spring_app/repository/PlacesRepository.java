package com.dh.bookings_spring_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.bookings_spring_app.entities.Places;

@Repository
public interface PlacesRepository extends JpaRepository<Places, Integer> {
    Optional<Places> findPlacesByName(String name);
}
