package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Entity.Airline;

import java.sql.SQLException;
import java.util.List;

public interface AirlineService {

    List<Airline> getAllList() throws SQLException;

    Airline getByAirlineCode(String airlineCode) throws SQLException;
}
