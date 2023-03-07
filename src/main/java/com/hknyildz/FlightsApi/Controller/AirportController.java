package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "airports",produces = "application/json")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping
    public @ResponseBody List<Airport> getAllAirports() {

        return airportService.getAllList();
    }

    @GetMapping("/{airportCode}")
    public Airport getByAirportCode(@PathVariable("airportCode") String airportCode) {
        return airportService.getByAirportCode(airportCode);
    }
}
