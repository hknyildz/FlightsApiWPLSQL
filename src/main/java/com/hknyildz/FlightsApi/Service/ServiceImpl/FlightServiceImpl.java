package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Flight;
import com.hknyildz.FlightsApi.Repository.FlightRepository;
import com.hknyildz.FlightsApi.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Override
    public List<Flight> getAllList() {
        return (List<Flight>) flightRepository.findAll();
    }

    @Override
    public Flight createOrUpdate(FlightDto flightDto) {
        return null;
    }

    @Override
    public String deleteById(String flightId) {
        return null;
    }
}
