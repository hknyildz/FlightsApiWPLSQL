package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Entity.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport,Long> {
}
