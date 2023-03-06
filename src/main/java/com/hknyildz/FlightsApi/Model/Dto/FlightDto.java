package com.hknyildz.FlightsApi.Model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FlightDto {

    private String id;

    @Size(min = 3, message = "Airline Code should have at least 3 characters")
    private String departureAirportCode;

    @Size(min = 3, message = "Airline Code should have at least 3 characters")
    private String arrivalAirportCode;

    @Size(min = 2, message = "Airline Code should have at least 2 characters")
    private String airplaneCode;

    @NotBlank(message = "departureTime is required")
    private String departureTime;

    @NotBlank(message = "arrivalTime is required")
    private String arrivalTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public void setDepartureAirportCode(String departureAirportCode) {
        this.departureAirportCode = departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getAirplaneCode() {
        return airplaneCode;
    }

    public void setAirplaneCode(String airplaneCode) {
        this.airplaneCode = airplaneCode;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalDateTime) {
        this.arrivalTime = arrivalDateTime;
    }
}
