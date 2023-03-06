package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Repository.AirportRepository;
import com.hknyildz.FlightsApi.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<Airport> getAllList() {
        List<Airport> airports = (List<Airport>) airportRepository.findAll();
        if (airports.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return airports;
    }

    @Override
    public Airport createOrUpdate(AirportDto airportDto) {
        Airport airport = airportRepository.findByAirportCode(airportDto.getAirportCode());
        if (airport == null) {
            airport = new Airport();
            airport.setAirportCode(airportDto.getAirportCode());
        }
        airport.setName(airportDto.getName());
        return airportRepository.save(airport);
    }

    @Override
    public Airport getByAirportCode(String airportCode) {

        Airport airport = airportRepository.findByAirportCode(airportCode);
        if (airport != null) {
            return airport;
        } else {
            throw new EntityNotFoundException("Airport not found by code:" + airportCode);
        }

    }


}
