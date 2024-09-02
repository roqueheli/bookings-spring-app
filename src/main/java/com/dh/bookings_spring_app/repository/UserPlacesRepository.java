package com.dh.bookings_spring_app.repository;

import com.dh.bookings_spring_app.entities.UserPlaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPlacesRepository extends JpaRepository<UserPlaces, Integer> {
    // Método personalizado para encontrar todas las relaciones por userId
    @Query(value = "SELECT * FROM user_places WHERE user_id = :id", nativeQuery = true)
    List<UserPlaces> findByUserUserId(Integer id);

    // Método personalizado para encontrar todas las relaciones por placeId
    @Query(value = "SELECT * FROM user_places WHERE place_id = :id", nativeQuery = true)
    List<UserPlaces> findByPlacePlaceId(Integer id);
}
