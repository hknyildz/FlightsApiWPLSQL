package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Entity.Flight;
import com.hknyildz.FlightsApi.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightRepository flightRepository;

    @GetMapping
    public @ResponseBody List<Flight> getAllFlights(){

        return (List<Flight>) flightRepository.findAll();
    }

}
