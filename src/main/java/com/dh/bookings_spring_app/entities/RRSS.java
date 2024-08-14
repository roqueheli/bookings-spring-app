package com.dh.bookings_spring_app.entities;

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
@Table(name = "rrss")
public class RRSS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rrss_id")
    private Integer rrssId;

    @Column(name = "rrss_name")
    private String rrssName;

    @OneToMany(mappedBy = "rrss", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PlacesRRSS> placeRrss;

    public Integer getRrssId() {
        return rrssId;
    }

    public void setRrssId(Integer rrssId) {
        this.rrssId = rrssId;
    }

    public String getRrssName() {
        return rrssName;
    }

    public void setRrssName(String rrssName) {
        this.rrssName = rrssName;
    }

    public Set<PlacesRRSS> getPlaceRrss() {
        return placeRrss;
    }

    public void setPlaceRrss(Set<PlacesRRSS> placeRrss) {
        this.placeRrss = placeRrss;
    }
}
