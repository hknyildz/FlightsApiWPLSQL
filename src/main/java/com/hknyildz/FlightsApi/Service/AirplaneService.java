package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;

import java.sql.SQLException;
import java.util.List;

public interface AirplaneService {
    List<AirplaneDto> getAllList() throws SQLException;

    AirplaneDto getByAirplaneCode(String airplaneCode) throws SQLException;

    List<AirplaneDto> getByAirlineCode(String airlineCode) throws SQLException;
}
