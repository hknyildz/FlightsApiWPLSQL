package com.hknyildz.FlightsApi.Repository;

import com.hknyildz.FlightsApi.Dao.AirplaneDao;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Long> {

    @Query("select airplane from Airplane airplane where airplane.airplaneCode = :code")
    Airplane findByAirplaneCode(@Param("code") String airplaneCode);

    @Query("select airplane from Airplane airplane where airplane.airline.airlineCode = :code")
    List<Airplane> findByAirlineCode(@Param("code") String airlineCode);
}
