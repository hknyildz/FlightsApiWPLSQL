package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Model.Entity.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightRepository extends CrudRepository<Flight, String> {

    @Query("select flight from Flight  flight where flight.arrivalAirportCode= :code")
    List<Flight> findByArrivalAirportCode(@Param("code") String arrivalAirportCode);

    @Query("select flight from Flight  flight where flight.departureAirportCode= :code")
    List<Flight> findByDepartureAirportCode(@Param("code") String departureAirportCode);


    @Query("select COUNT(flight) from Flight flight where " +
            "((flight.departureAirportCode= :departureAirportCode AND flight.arrivalAirportCode= :arrivalAirportCode)" +
            " or (flight.departureAirportCode= :arrivalAirportCode AND flight.arrivalAirportCode= :departureAirportCode))" +
            "AND (year(flight.departureTime) = year(:departureDate) and month(flight.departureTime) = month(:departureDate) and day(flight.departureTime) = day(:departureDate))")
    int getDailyFlightCountBetweenAirports(String arrivalAirportCode, String departureAirportCode, @Param("departureDate") LocalDate departureDate);
}
