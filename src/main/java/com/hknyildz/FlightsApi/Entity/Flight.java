package com.hknyildz.FlightsApi.Entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Entity
@Table(name = "T_FLIGHT")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DEPARTURE_AIRPORT_CODE", referencedColumnName = "AIRPORT_CODE")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "ARRIVAL_AIRPORT_CODE", referencedColumnName = "AIRPORT_CODE")
    private Airport arrivalAirport;

    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm")
    @Column(name = "ARRIVAL_TIME")
    private LocalDateTime departureTime;

    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm")
    @Column(name = "DEPARTURE_TIME")
    private LocalDateTime arrivalTime;

    @ManyToOne
    @JoinColumn(name = "AIRPLANE_CODE", referencedColumnName = "AIRPLANE_CODE")
    private Airplane airplane;

    public Flight(Airplane airplane, Airport departureAirport, LocalDateTime departureTime, Airport arrivalAirport, LocalDateTime arrivalTime) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airplane = airplane;
    }

    private String getDuration() {
        return departureTime.until(arrivalTime, ChronoUnit.MINUTES) + "Minutes";
    }

    public LocalDateTime getDepartureDate() {
        return departureTime;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureTime = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalTime;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalTime = arrivalDate;
    }

    public Long getId() {
        return id;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(Airport departureAirport) {
        this.departureAirport = departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }
}
