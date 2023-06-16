package com.hknyildz.FlightsApi.Service;


import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Flight;

import java.sql.SQLException;
import java.util.List;

public interface FlightService {
    List<FlightDto> getAllList() throws SQLException;

    String createOrUpdate(FlightDto flightDto);

    List<FlightDto> getFlightsByDepartureAirport(String airportCode);

    List<FlightDto> getFlightsByArrivalAirport(String airportCode);

    void deleteById(String flightId);
}
