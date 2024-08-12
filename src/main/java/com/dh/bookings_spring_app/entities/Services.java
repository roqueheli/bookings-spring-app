package com.dh.bookings_spring_app.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id")
    private Integer service_id;

    @Column(name = "name")
    private String name;

    public Integer getService_id() {
        return service_id;
    }

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<PlaceService> placeServices = new HashSet<>();

    public void setService_id(Integer service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlaceService> getPlaceServices() {
        return placeServices;
    }

    public void setPlaceServices(Set<PlaceService> placeServices) {
        this.placeServices = placeServices;
    }
}
