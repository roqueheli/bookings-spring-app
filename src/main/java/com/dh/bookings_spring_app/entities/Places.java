package com.dh.bookings_spring_app.entities;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "places")
public class Places {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_id")
    private Integer place_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "location")
    private String location;

    @Column(name = "calification")
    private Float calification;

    @Column(name = "user_creation_id")
    private Integer userCreationId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", nullable = false)
    private Addresses address;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories category;

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserPlaces> userplaces = new HashSet<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlacesRRSS> placesRRSSs = new HashSet<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Images> images = new HashSet<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Rooms> rooms = new HashSet<>();

    @OneToMany(mappedBy = "place", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PlaceService> placeServices = new HashSet<>();

    public Integer getPlace_id() {
        return place_id;
    }

    public void setPlace_id(Integer place_id) {
        this.place_id = place_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Float getCalification() {
        return calification;
    }

    public void setCalification(Float calification) {
        this.calification = calification;
    }

    public Set<Images> getImages() {
        return images;
    }

    public void setImages(Set<Images> images) {
        this.images = images;
    }

    public Set<PlaceService> getPlaceServices() {
        return placeServices;
    }

    public void setPlaceServices(Set<PlaceService> placeServices) {
        this.placeServices = placeServices;
    }

    public Addresses getAddress() {
        return address;
    }

    public void setAddress(Addresses address) {
        this.address = address;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Set<Rooms> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Rooms> rooms) {
        this.rooms = rooms;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<PlacesRRSS> getPlacesRRSSs() {
        return placesRRSSs;
    }

    public void setPlacesRRSSs(Set<PlacesRRSS> placesRRSSs) {
        this.placesRRSSs = placesRRSSs;
    }

    public Set<UserPlaces> getUserplaces() {
        return userplaces;
    }

    public void setUserplaces(Set<UserPlaces> userplaces) {
        this.userplaces = userplaces;
    }

    public Integer getUserCreationId() {
        return userCreationId;
    }

    public void setUserCreationId(Integer userCreationId) {
        this.userCreationId = userCreationId;
    }
}
