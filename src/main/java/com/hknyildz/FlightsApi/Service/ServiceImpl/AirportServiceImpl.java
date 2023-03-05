package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Dao.AirportDao;
import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    AirportDao airportDao;

    @Override
    public List<Airport> getAllList() {
        return airportDao.getAll();
    }

    @Override
    public HashMap<String, String> createOrUpdate(AirportDto airportDto) {
        Airport airport = null;
        if (airportDto.getAirportCode() != null) {
            airport = airportDao.findByAirportCode(airportDto.getAirportCode());
            if (airport == null) {
                airport = new Airport();
                airport.setAirportCode(airportDto.getAirportCode());
            }
        } else {
            airport = new Airport();
            airport.setAirportCode(airportDto.getAirportCode());
        }
        airport.setName(airportDto.getName());
        airportDao.createOrUpdateAirport(airport);
        HashMap<String, String> map = new HashMap<>();
        map.put("name", airport.getName());
        map.put("AirportCode", airport.getAirportCode());
        map.put("status", "201");
        return map;
    }

    @Override
    public Airport getByAirportCode(String airportCode) {

        Airport airport = airportDao.findByAirportCode(airportCode);
        if (airport != null) {
            return airport;
        } else {
            throw new RuntimeException("Airport not found by code:" + airportCode);
        }

    }


}
