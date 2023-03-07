package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Repository.AirlineRepository;
import com.hknyildz.FlightsApi.Repository.AirplaneRepository;
import com.hknyildz.FlightsApi.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    AirplaneRepository airplaneRepository;

    @Autowired
    AirlineRepository airlineRepository;

    @Override
    public List<Airplane> getAllList() {
        List<Airplane> airplanes = (List<Airplane>) airplaneRepository.findAll();
        if (airplanes.isEmpty()) {
            throw new EntityNotFoundException("There is no airplanes exists");
        } else return airplanes;
    }

    @Override
    public Airplane getByAirplaneCode(String airplaneCode) {
        Airplane airplane = airplaneRepository.findByAirplaneCode(airplaneCode);
        if (airplane == null) {
            throw new EntityNotFoundException("There is no plane with code:" + airplaneCode);
        }
        return airplane;
    }

    @Override
    public List<Airplane> getByAirlineCode(String airlineCode) {
        List<Airplane> airplanes = airplaneRepository.findByAirlineCode(airlineCode);
        if (airplanes.isEmpty()) {
            throw new EntityNotFoundException("There is no plane with airline code:" + airlineCode);
        }
        return airplanes;
    }


}
