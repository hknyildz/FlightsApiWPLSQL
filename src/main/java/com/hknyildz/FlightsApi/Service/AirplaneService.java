package com.hknyildz.FlightsApi.Service;

import com.hknyildz.FlightsApi.Model.Entity.Airplane;

import java.util.List;

public interface AirplaneService {
    List<Airplane> getAllList();

    Airplane getByAirplaneCode(String airplaneCode);

    List<Airplane> getByAirlineCode(String airlineCode);
}
