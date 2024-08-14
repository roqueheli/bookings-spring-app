package com.dh.bookings_spring_app.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "place_rrss")
public class PlacesRRSS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "place_rrss_id")
    private Integer placeRrssId;

    @Column(name = "rrss_url")
    private String rrssUrl;

    @ManyToOne
    @JoinColumn(name = "place_id", referencedColumnName = "place_id", nullable = false)
    private Places place;

    @ManyToOne
    @JoinColumn(name = "rrss_id", referencedColumnName = "rrss_id", nullable = false)
    private RRSS rrss;

    public Integer getPlaceRrssId() {
        return placeRrssId;
    }

    public void setPlaceRrssId(Integer placeRrssId) {
        this.placeRrssId = placeRrssId;
    }

    public Places getPlace() {
        return place;
    }

    public void setPlace(Places place) {
        this.place = place;
    }

    public RRSS getRrss() {
        return rrss;
    }

    public void setRrss(RRSS rrss) {
        this.rrss = rrss;
    }

    public String getRrssUrl() {
        return rrssUrl;
    }

    public void setRrssUrl(String rrssUrl) {
        this.rrssUrl = rrssUrl;
    }
}
