package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Dao.AirlineDao;
import com.hknyildz.FlightsApi.Dao.AirplaneDao;
import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    AirplaneDao airplaneDao;

    @Autowired
    AirlineDao airlineDao;

    @Override
    public List<Airplane> getAllList() {
        return airplaneDao.getAll();
    }

    @Override
    public Airplane createOrUpdate(AirplaneDto airplaneDto) {

        Airplane airplane = null;
        if (airplaneDto.getAirplaneCode() != null) {
            airplane = airplaneDao.findByAirplaneCode(airplaneDto.getAirplaneCode());
            if (airplane == null) {
                airplane = new Airplane();
                airplane.setAirplaneCode(airplaneDto.getAirplaneCode());
            }
        } else {
            airplane = new Airplane();
            airplane.setAirplaneCode(airplaneDto.getAirplaneCode());
        }
        Airline airline = airlineDao.findAirlineByAirlineCode(airplaneDto.getAirlineCode());
        if(airline!=null){
            airplane.setAirline(airline);
        }
        else{
            throw new RuntimeException("There is no airline with code:"+ airplaneDto.getAirlineCode());
        }
        return airplaneDao.createOrUpdateAirplane(airplane);
    }

    @Override
    public Airplane getByAirplaneCode(String airplaneCode) {
        return airplaneDao.findByAirplaneCode(airplaneCode);
    }

    @Override
    public List<Airplane> getByAirlineCode(String airlineCode) {
        return airplaneDao.findByAirlineCode(airlineCode);
    }

    @Override
    public int removeByAirplaneCode(String airplaneCode) {
        Airplane airplane=airplaneDao.findByAirplaneCode(airplaneCode);

        if(airplane!=null)
        {
            return airplaneDao.DeleteAirplane(airplane);
        }
        else{
            throw new RuntimeException("There is no airplane with code:"+airplaneCode);
        }


    }

}
