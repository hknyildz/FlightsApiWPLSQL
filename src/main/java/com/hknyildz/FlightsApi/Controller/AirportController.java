package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Entity.Airport;
import com.hknyildz.FlightsApi.Repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("airports")
public class AirportController {

    @Autowired
    AirportRepository airportRepository;

    @GetMapping
    public @ResponseBody List<Airport> getAllAirports(){

        return (List<Airport>) airportRepository.findAll();
    }


}
