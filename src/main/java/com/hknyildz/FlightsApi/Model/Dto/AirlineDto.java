package com.hknyildz.FlightsApi.Model.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AirlineDto {

    @NotBlank(message = "name field is required")
    private String name;

    @Size(min = 3, message = "Airline Code should have at least 3 characters")
    private String airlineCode;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }
}
