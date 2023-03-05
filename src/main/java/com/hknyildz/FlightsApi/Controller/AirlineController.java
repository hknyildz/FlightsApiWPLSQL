package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("airlines")
public class AirlineController {

    @Autowired
    AirlineRepository airlineRepository;

    @GetMapping
    public @ResponseBody List<Airline> getAllAirlines() {

        return (List<Airline>) airlineRepository.findAll();
    }
}
