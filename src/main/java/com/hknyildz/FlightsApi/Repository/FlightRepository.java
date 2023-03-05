package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Model.Entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {

    @Query("select flight from Flight  flight where flight.arrivalAirport= :code")
    Flight findByArrivalAirportCode(@Param("code") String arrivalAirportCode);

    @Query("select flight from Flight  flight where flight.departureAirport= :code")
    Flight findByDepartureAirportCode(@Param("code") String departureAirportCode);
}
