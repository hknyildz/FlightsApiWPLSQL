package com.hknyildz.FlightsApi.Dao;

import com.hknyildz.FlightsApi.Model.Entity.Airport;

import java.util.List;

public interface AirportDao {

    List<Airport> getAll();

    Airport findByAirportCode(String airportCode);

    Airport createOrUpdateAirport(Airport airport);

}
