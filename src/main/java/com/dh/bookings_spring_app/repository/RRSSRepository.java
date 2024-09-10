package com.dh.bookings_spring_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dh.bookings_spring_app.entities.RRSS;

@Repository
public interface RRSSRepository extends JpaRepository<RRSS, Integer> {
    
}
