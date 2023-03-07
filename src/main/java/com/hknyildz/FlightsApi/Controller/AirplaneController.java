package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "airplanes",produces = "application/json")
public class AirplaneController {

    @Autowired
    AirplaneService airplaneService;

    @GetMapping
    public @ResponseBody List<Airplane> getAllAirplanes() {

        return airplaneService.getAllList();
    }

    @GetMapping("/{airplaneCode}")
    public Airplane getByAirplaneCode(@PathVariable("airplaneCode") String airplaneCode) {
        return airplaneService.getByAirplaneCode(airplaneCode);
    }

    @GetMapping(params = "airline")
    public List<Airplane> getByAirlineCode(@RequestParam("airline") String airlineCode) {
        return airplaneService.getByAirlineCode(airlineCode);
    }

}
