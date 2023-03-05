package com.hknyildz.FlightsApi.Dao.DaoImpl;

import com.hknyildz.FlightsApi.Dao.AirportDao;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirportDaoImpl implements AirportDao {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public List<Airport> getAll() {
        return (List<Airport>) airportRepository.findAll();
    }


    @Override
    public Airport findByAirportCode(String airportCode) {
        if (!airportCode.trim().isBlank()) {
            return airportRepository.findByAirportCode(airportCode);
        } else {
            throw new RuntimeException("Airport Code cannot be null");
        }
    }

    @Override
    public Airport createOrUpdateAirport(Airport airport) {
        return airportRepository.save(airport);
    }


}
