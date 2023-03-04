package com.hknyildz.FlightsApi.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "T_AIRPLANE")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "AIRLINE_CODE", referencedColumnName = "AIRLINE_CODE")
    private Airline airline;

    @Column(name = "AIRPLANE_CODE", unique = true)
    private String airplaneCode;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, mappedBy = "airplane")
    @JsonIgnore
    Set<Flight> flights = new HashSet<>();

    public Airplane(Airline airline, String airplaneCode) {
        this.airline = airline;
        this.airplaneCode = airplaneCode;
    }

    public Long getId() {
        return id;
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
