package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Entity.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> getAllList();

    Airline getByAirlineCode(String airlineCode);
}
