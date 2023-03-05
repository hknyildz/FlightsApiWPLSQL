package com.hknyildz.FlightsApi.Dao.DaoImpl;

import com.hknyildz.FlightsApi.Dao.AirlineDao;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirlineDaoImpl implements AirlineDao {

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public List<Airline> getAll() {
        return (List<Airline>) airlineRepository.findAll();
    }

    @Override
    public Airline findAirlineByAirlineCode(String airlineCode) {
        return airlineRepository.findAirlineByAirlineCode(airlineCode);
    }


    @Override
    public Airline createOrUpdateAirline(Airline airline) {
        return airlineRepository.save(airline);
    }
}
