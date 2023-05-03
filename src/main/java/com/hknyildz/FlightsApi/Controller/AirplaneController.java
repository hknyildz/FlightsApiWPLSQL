package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "airplanes",produces = "application/json")
public class AirplaneController {

    @Autowired
    AirplaneService airplaneService;

    @GetMapping
    public @ResponseBody List<AirplaneDto> getAllAirplanes() throws SQLException {

        return airplaneService.getAllList();
    }

    @GetMapping("/{airplaneCode}")
    public AirplaneDto getByAirplaneCode(@PathVariable("airplaneCode") String airplaneCode) throws SQLException {
        return airplaneService.getByAirplaneCode(airplaneCode);
    }

    @GetMapping(params = "airline")
    public List<AirplaneDto> getByAirlineCode(@RequestParam("airline") String airlineCode) throws SQLException {
        return airplaneService.getByAirlineCode(airlineCode);
    }

}
