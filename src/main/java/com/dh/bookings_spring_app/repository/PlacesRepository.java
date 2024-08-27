package com.dh.bookings_spring_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dh.bookings_spring_app.entities.Places;

@Repository
public interface PlacesRepository extends JpaRepository<Places, Integer> {
    Optional<Places> findPlacesByName(String name);

    @Query(value = "SELECT * FROM places ORDER BY RAND() LIMIT 10", nativeQuery = true)
    List<Places> findRandomProducts();

    @Query(value = "SELECT * FROM places WHERE user_id = :id ORDER BY name", nativeQuery = true)
    Optional<List<Places>> findPlacesByOwner(Integer id);
}
