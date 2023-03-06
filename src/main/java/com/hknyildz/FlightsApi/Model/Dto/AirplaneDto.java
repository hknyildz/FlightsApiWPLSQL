package com.hknyildz.FlightsApi.Model.Dto;

import jakarta.validation.constraints.Size;

public class AirplaneDto {

    @Size(min = 3, message = "Airline Code should have at least 3 characters")
    private String airlineCode;

    @Size(min = 2, message = "Airplane Code should have at least 2 characters")
    private String airplaneCode;


    public String getAirlineCode() {
        return airlineCode;
    }

    public void setAirlineCode(String airlineCode) {
        this.airlineCode = airlineCode;
    }

    public String getAirplaneCode() {
        return airplaneCode;
    }

    public void setAirplaneCode(String airplaneCode) {
        this.airplaneCode = airplaneCode;
    }
}
