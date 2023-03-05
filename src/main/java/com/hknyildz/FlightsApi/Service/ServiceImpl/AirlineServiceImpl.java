package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Model.Dto.AirlineDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Repository.AirlineRepository;
import com.hknyildz.FlightsApi.Service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public List<Airline> getAllList() {
        return (List<Airline>) airlineRepository.findAll();
    }

    @Override
    public Airline createOrUpdate(AirlineDto airlineDto) {

        Airline airline = airlineRepository.findAirlineByAirlineCode(airlineDto.getAirlineCode());
        if (airline == null) {
            airline = new Airline();
            airline.setAirlineCode(airline.getAirlineCode());
        }
        airline.setName(airlineDto.getName());
        return airlineRepository.save(airline);
    }

    @Override
    public Airline getByAirlineCode(String airlineCode) {
        return airlineRepository.findAirlineByAirlineCode(airlineCode);
    }

}
