package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Entity.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane,Long> {
}
