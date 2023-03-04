package com.hknyildz.FlightsApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "T_AIRPORT")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, name = "AIRPORT_CODE")
    private String airportCode;

    private String name;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "arrivalAirport")
    @JsonIgnore
    Set<Flight> arrivingflights = new HashSet<>();

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "departureAirport")
    @JsonIgnore
    Set<Flight> departingflights = new HashSet<>();

    public Airport(String name,String airportCode) {
        this.airportCode = airportCode;
        this.name = name;
    }

    public Airport() {

    }

    public Long getId() {
        return id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Flight> getArrivingflights() {
        return arrivingflights;
    }

    public void setArrivingflights(Set<Flight> arrivingflights) {
        this.arrivingflights = arrivingflights;
    }

    public Set<Flight> getDepartingflights() {
        return departingflights;
    }

    public void setDepartingflights(Set<Flight> departingflights) {
        this.departingflights = departingflights;
    }
}
