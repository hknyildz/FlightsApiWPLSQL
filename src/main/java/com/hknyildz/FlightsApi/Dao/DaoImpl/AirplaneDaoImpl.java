package com.hknyildz.FlightsApi.Dao.DaoImpl;

import com.hknyildz.FlightsApi.Dao.AirplaneDao;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AirplaneDaoImpl implements AirplaneDao {

    @Autowired
    AirplaneRepository airplaneRepository;

    @Override
    public List<Airplane> getAll() {
        return (List<Airplane>) airplaneRepository.findAll();
    }

    @Override
    public Airplane findByAirplaneCode(String airplaneCode) {
        if (!airplaneCode.trim().isBlank()) {
            return airplaneRepository.findByAirplaneCode(airplaneCode);
        } else {
            throw new RuntimeException("airplane Code cannot be null");
        }
    }

    @Override
    public List<Airplane> findByAirlineCode(String airlineCode) {
        if (!airlineCode.trim().isBlank()) {
            return airplaneRepository.findByAirlineCode(airlineCode);
        } else {
            throw new RuntimeException("airplane Code cannot be null");
        }
    }

    @Override
    public Airplane createOrUpdateAirplane(Airplane airplane) {
        return airplaneRepository.save(airplane);
    }

    @Override
    public int DeleteAirplane(Airplane airplane) {
        airplaneRepository.delete(airplane);
        return 1;
    }
}
