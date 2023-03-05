package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @GetMapping
    public @ResponseBody List<Airport> getAllAirports() {

        return (List<Airport>) airportService.getAllList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> createOrUpdate(@RequestBody AirportDto airportDto) {
        return airportService.createOrUpdate(airportDto);
    }

    @GetMapping("/{airportCode}")
    public Airport getByAirportCode(@PathVariable("airportCode") String airportCode) {
        return airportService.getByAirportCode(airportCode);
    }
}
