package com.hknyildz.FlightsApi.Dao;

import com.hknyildz.FlightsApi.Model.Entity.Airplane;

import java.util.List;

public interface AirplaneDao {

    List<Airplane> getAll();

    Airplane findByAirplaneCode(String airplaneCode);

    List<Airplane> findByAirlineCode(String airlineCode);

    Airplane createOrUpdateAirplane(Airplane airplane);

    int DeleteAirplane(Airplane airplane);

}
