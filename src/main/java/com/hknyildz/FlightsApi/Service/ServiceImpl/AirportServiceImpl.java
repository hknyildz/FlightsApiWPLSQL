package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Repo.AirportRepo;
import com.hknyildz.FlightsApi.Repository.AirportRepository;
import com.hknyildz.FlightsApi.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AirportServiceImpl implements AirportService {
    @Autowired
    AirportRepo airportRepo;

    @Override
    public List<AirportDto> getAllList() throws SQLException {
        List<AirportDto> airports = airportRepo.findAll();
        if (airports.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return airports;
    }

    @Override
    public AirportDto getByAirportCode(String airportCode) throws SQLException {

        AirportDto airport = airportRepo.findByAirportCode(airportCode);
        if (airport != null) {
            return airport;
        } else {
            throw new EntityNotFoundException("Airport not found by code:" + airportCode);
        }

    }

}
