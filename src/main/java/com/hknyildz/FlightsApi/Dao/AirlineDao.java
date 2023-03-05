package com.hknyildz.FlightsApi.Dao;

import com.hknyildz.FlightsApi.Model.Entity.Airline;

import java.util.List;

public interface AirlineDao {

    List<Airline> getAll();

    Airline findAirlineByAirlineCode(String airlineCode);

    Airline createOrUpdateAirline(Airline airline);
}
