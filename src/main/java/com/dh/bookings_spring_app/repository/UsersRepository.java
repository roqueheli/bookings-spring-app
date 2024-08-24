package com.dh.bookings_spring_app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dh.bookings_spring_app.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE user_id = :id OR (owner_id = :id OR owner_id IS NULL)", nativeQuery = true)
    List<Users> findByOwner(Integer id);
}
