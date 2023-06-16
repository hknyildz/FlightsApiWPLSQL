package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Flight;
import com.hknyildz.FlightsApi.Service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "flights",produces = "application/json")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping
    public @ResponseBody List<FlightDto> getAllFlights() throws SQLException {
        return flightService.getAllList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrUpdate(@Valid @RequestBody FlightDto flightDto) {
        return flightService.createOrUpdate(flightDto);
    }

    @GetMapping(params = "arrival")
    public List<FlightDto> getByArrivalAirportCode(@RequestParam("arrival") String airportCode) {
        return flightService.getFlightsByArrivalAirport(airportCode);
    }

    @GetMapping(params = "departure")
    public List<FlightDto> getByDepartureAirportCode(@RequestParam("departure") String airportCode) {
        return flightService.getFlightsByDepartureAirport(airportCode);
    }

    @RequestMapping(value = {"/{flightId}"}, method = RequestMethod.DELETE)
    public void deleteAirplane(@PathVariable("flightId") String flightId) {
        flightService.deleteById(flightId);
    }

}
