package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Entity.Airline;
import org.springframework.data.repository.CrudRepository;

public interface AirlineRepository extends CrudRepository<Airline,Long> {
}
