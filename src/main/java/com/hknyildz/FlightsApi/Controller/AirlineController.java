package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "airlines",produces = "application/json")
public class AirlineController {

    @Autowired
    AirlineService airlineService;

    @GetMapping
    public @ResponseBody List<Airline> getAllAirlines() throws SQLException {

        return airlineService.getAllList();
    }

    @GetMapping("/{airlineCode}")
    public Airline getByAirlineCode(@PathVariable("airlineCode") String airlineCode) throws SQLException {
        return airlineService.getByAirlineCode(airlineCode);
    }

}
