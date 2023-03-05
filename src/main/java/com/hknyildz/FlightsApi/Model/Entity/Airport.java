package com.hknyildz.FlightsApi.Model.Entity;

import jakarta.persistence.*;


@Entity
@Table(name = "T_AIRPORT")
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, name = "AIRPORT_CODE")
    private String airportCode;

    private String name;

    public Airport(String name, String airportCode) {
        this.airportCode = airportCode;
        this.name = name;
    }

    public Airport() {

    }

    public String getId() {
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

}
