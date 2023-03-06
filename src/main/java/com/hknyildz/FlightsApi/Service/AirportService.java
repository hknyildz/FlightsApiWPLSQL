package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Entity.Airport;

import java.util.HashMap;
import java.util.List;

public interface AirportService {

    List<Airport> getAllList();

    Airport createOrUpdate(AirportDto airportDto);

    Airport getByAirportCode(String airportCode);


}
