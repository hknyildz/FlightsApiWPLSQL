package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Dao.AirlineDao;
import com.hknyildz.FlightsApi.Model.Dto.AirlineDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirlineServiceImpl implements AirlineService {

    @Autowired
    AirlineDao airlineDao;

    @Override
    public List<Airline> getAllList() {
        return airlineDao.getAll();
    }

    @Override
    public Airline createOrUpdate(AirlineDto airlineDto) {

        Airline airline = airlineDao.findAirlineByAirlineCode(airlineDto.getAirlineCode());
        if (airline == null) {
            airline = new Airline();
            airline.setAirlineCode(airline.getAirlineCode());
        } else {
            airline = new Airline();
            airline.setAirlineCode(airlineDto.getAirlineCode());
        }
        airline.setName(airlineDto.getName());
        return airlineDao.createOrUpdateAirline(airline);
    }

    @Override
    public Airline getByAirlineCode(String airlineCode) {
        return airlineDao.findAirlineByAirlineCode(airlineCode);
    }

}
