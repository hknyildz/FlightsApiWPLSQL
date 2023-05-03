package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Entity.Airport;

import java.sql.SQLException;
import java.util.List;

public interface AirportService {

    List<AirportDto> getAllList() throws SQLException;

    AirportDto getByAirportCode(String airportCode) throws SQLException;


}
