package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Dto.AirlineDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;

import java.util.List;

public interface AirlineService {

    List<Airline> getAllList();

    Airline createOrUpdate(AirlineDto airlineDto);

    Airline getByAirlineCode(String airlineCode);
}
