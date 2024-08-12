package com.dh.bookings_spring_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "place_service")
public class PlaceService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_service_id")
    private Integer placeServiceId;

    @ManyToOne
    @JoinColumn(name = "place_id")
    @JsonIgnore
    private Places place;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Services service;

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Integer getPlaceServiceId() {
        return placeServiceId;
    }

    public void setPlaceServiceId(Integer placeServiceId) {
        this.placeServiceId = placeServiceId;
    }
}
