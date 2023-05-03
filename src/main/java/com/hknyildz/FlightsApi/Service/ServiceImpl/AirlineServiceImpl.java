package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Repo.AirlineRepo;
import com.hknyildz.FlightsApi.Service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineRepo airlineRepo;
    @Override
    public List<Airline> getAllList() throws SQLException {
        List<Airline> airlines = (List<Airline>) airlineRepo.findAll();
        if (airlines.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return airlines;
    }

    @Override
    public Airline getByAirlineCode(String airlineCode) throws SQLException {
        Airline airline = airlineRepo.findAirlineByAirlineCode(airlineCode);
        if (airline == null) {
            throw new EntityNotFoundException("There is no airline with code:" + airlineCode);
        } else {
            return airline;
        }
    }

}
