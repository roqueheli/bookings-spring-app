package com.dh.bookings_spring_app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "place_room")
public class PlaceRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_room_id")
    private Integer placeRoomId;

    @ManyToOne
    @JoinColumn(name = "place_id")
    private Places place;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Rooms room;

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public Integer getPlaceRoomId() {
        return placeRoomId;
    }

    public void setPlaceRoomId(Integer placeRoomId) {
        this.placeRoomId = placeRoomId;
    }
}
