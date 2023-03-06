package com.hknyildz.FlightsApi.Model.Dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AirportDto {


    @NotBlank(message = "name field is required")
    private String name;

    @Size(min = 3, message = "Airport Code should have at least 3 characters")
    private String airportCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }
}
