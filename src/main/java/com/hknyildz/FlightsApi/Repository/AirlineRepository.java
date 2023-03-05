package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Model.Entity.Airline;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AirlineRepository extends CrudRepository<Airline, Long> {

    @Query("select airline from Airline  airline where airline.airlineCode= :code")
    Airline findAirlineByAirlineCode(@Param("code")String airlineCode);
}
