package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Repo.AirlineRepo;
import com.hknyildz.FlightsApi.Repo.AirplaneRepo;
import com.hknyildz.FlightsApi.Repository.AirlineRepository;
import com.hknyildz.FlightsApi.Repository.AirplaneRepository;
import com.hknyildz.FlightsApi.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    AirplaneRepository airplaneRepository;

    @Autowired
    AirplaneRepo airplaneRepo;

    @Override
    public List<AirplaneDto> getAllList() throws SQLException {
        List<AirplaneDto> airplanes = airplaneRepo.findAll();
        if (airplanes.isEmpty()) {
            throw new EntityNotFoundException("There is no airplanes exists");
        } else return airplanes;
    }

    @Override
    public AirplaneDto getByAirplaneCode(String airplaneCode) throws SQLException {
        AirplaneDto airplane = airplaneRepo.findByAirplaneCode(airplaneCode);
        if (airplane == null) {
            throw new EntityNotFoundException("There is no plane with code:" + airplaneCode);
        }
        return airplane;
    }

    @Override
    public List<AirplaneDto> getByAirlineCode(String airlineCode) throws SQLException {
        List<AirplaneDto> airplanes = airplaneRepo.findByAirlineCode(airlineCode);
        if (airplanes.isEmpty()) {
            throw new EntityNotFoundException("There is no plane with airline code:" + airlineCode);
        }
        return airplanes;
    }


}
