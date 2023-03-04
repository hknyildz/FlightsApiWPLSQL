package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Entity.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends CrudRepository<Flight,Long> {
}
