package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Dto.AirlineDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airlines")
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @GetMapping
    public @ResponseBody List<Airline> getAllAirlines() {

        return (List<Airline>) airlineService.getAllList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Airline createOrUpdate(@RequestBody AirlineDto airlineDto) {
        return airlineService.createOrUpdate(airlineDto);
    }

    @GetMapping("/{airlineCode}")
    public Airline getByAirlineCode(@PathVariable("airlineCode") String airlineCode) {
        return airlineService.getByAirlineCode(airlineCode);
    }

}
