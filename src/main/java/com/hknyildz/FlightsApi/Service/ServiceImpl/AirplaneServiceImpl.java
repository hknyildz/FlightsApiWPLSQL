package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
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
        return (List<Airplane>) airplaneRepository.findAll();
    }

    @Override
    public Airplane createOrUpdate(AirplaneDto airplaneDto) {

        Airplane airplane = airplaneRepository.findByAirplaneCode(airplaneDto.getAirplaneCode());
        if (airplane == null) {
            airplane = new Airplane();
            airplane.setAirplaneCode(airplaneDto.getAirplaneCode());
        }
        Airline airline = airlineRepository.findAirlineByAirlineCode(airplaneDto.getAirlineCode());
        if (airline != null) {
            airplane.setAirline(airline);
        } else {
            throw new RuntimeException("There is no airline with code:" + airplaneDto.getAirlineCode());
        }
        return airplaneRepository.save(airplane);
    }

    @Override
    public Airplane getByAirplaneCode(String airplaneCode) {
        return airplaneRepository.findByAirplaneCode(airplaneCode);
    }

    @Override
    public List<Airplane> getByAirlineCode(String airlineCode) {
        return airplaneRepository.findByAirlineCode(airlineCode);
    }

    @Override
    public String removeByAirplaneCode(String airplaneCode) {
        Airplane airplane = airplaneRepository.findByAirplaneCode(airplaneCode);

        if (airplane != null) {
            airplaneRepository.delete(airplane);
            return "SUCCESS";
        } else {
            throw new RuntimeException("There is no airplane with code:" + airplaneCode);
        }


    }

}
