package com.hknyildz.FlightsApi.Service;


import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Flight;

import java.util.List;

public interface FlightService {
    List<FlightDto> getAllList();

    Flight createOrUpdate(FlightDto flightDto);

    List<FlightDto> getFlightsByDepartureAirport(String airportCode);

    List<FlightDto> getFlightsByArrivalAirport(String airportCode);

    void deleteById(String flightId);
}
