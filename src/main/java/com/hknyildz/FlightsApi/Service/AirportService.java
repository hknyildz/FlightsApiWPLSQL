package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Entity.Airport;

import java.util.List;

public interface AirportService {

    List<Airport> getAllList();

    Airport getByAirportCode(String airportCode);


}
