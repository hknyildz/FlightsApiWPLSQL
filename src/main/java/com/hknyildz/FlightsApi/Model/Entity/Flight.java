package com.hknyildz.FlightsApi.Model.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "T_FLIGHT")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "DEPARTURE_AIRPORT_CODE")
    private String departureAirportCode;

    @Column(name = "ARRIVAL_AIRPORT_CODE")
    private String arrivalAirportCode;

    @Column(name = "DEPARTURE_TIME")
    private LocalDateTime departureTime;

    @Column(name = "ARRIVAL_TIME")
    private LocalDateTime arrivalTime;

    private String duration;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AIRPLANE_CODE", referencedColumnName = "AIRPLANE_CODE")
    private Airplane airplane;

    public Flight(Airplane airplane, String departureAirport, LocalDateTime departureTime, String arrivalAirport, LocalDateTime arrivalTime) {
        this.departureAirportCode = departureAirport;
        this.arrivalAirportCode = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airplane = airplane;
    }

    public Flight() {

    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public LocalDateTime getDepartureDate() {
        return departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalDate) {
        this.arrivalTime = arrivalDate;
    }

    public String getId() {
        return id;
    }

    public String getDepartureAirport() {
        return departureAirportCode;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirportCode = departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirportCode;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirportCode = arrivalAirport;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureDate) {
        this.departureTime = departureDate;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
