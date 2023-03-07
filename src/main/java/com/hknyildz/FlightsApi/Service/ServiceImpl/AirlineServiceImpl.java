package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
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
        List<Airline> airlines = (List<Airline>) airlineRepository.findAll();
        if (airlines.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return airlines;
    }

    @Override
    public Airline getByAirlineCode(String airlineCode) {
        Airline airline = airlineRepository.findAirlineByAirlineCode(airlineCode);
        if (airline == null) {
            throw new EntityNotFoundException("There is no airline with code:" + airlineCode);
        } else {
            return airline;
        }
    }

}
