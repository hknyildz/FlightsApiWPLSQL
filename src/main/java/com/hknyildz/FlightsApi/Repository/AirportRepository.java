package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Model.Entity.Airport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, String> {

    @Query("select airport from Airport airport where airport.airportCode = :code")
    Airport findByAirportCode(@Param("code") String airportCode);

}
