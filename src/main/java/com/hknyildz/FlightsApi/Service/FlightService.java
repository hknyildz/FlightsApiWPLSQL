package com.hknyildz.FlightsApi.Service;


import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Flight;

import java.util.List;

public interface FlightService {
    List<Flight> getAllList();

    Flight createOrUpdate(FlightDto flightDto);

    List<Flight> getFlightsByDepartureAirport(String airportCode);

    List<Flight> getFlightsByArrivalAirport(String airportCode);

    void deleteById(String flightId);
}
