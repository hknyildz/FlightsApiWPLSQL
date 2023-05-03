package com.hknyildz.FlightsApi.Model.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_AIRPLANE")
public class Airplane {

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "airplane")
    @JsonIgnore
    Set<Flight> flights = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne
    @JoinColumn(name = "AIRLINE_CODE", referencedColumnName = "AIRLINE_CODE")
    private Airline airline;
    @Column(name = "AIRPLANE_CODE", unique = true)
    private String airplaneCode;

    public Airplane(Airline airline, String airplaneCode) {
        this.airline = airline;
        this.airplaneCode = airplaneCode;
    }

    public Airplane() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Airline getAirline() {
        return airline;
    }

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public String getAirplaneCode() {
        return airplaneCode;
    }

    public void setAirplaneCode(String airplaneCode) {
        this.airplaneCode = airplaneCode;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public void setFlights(Set<Flight> flights) {
        this.flights = flights;
    }
}
