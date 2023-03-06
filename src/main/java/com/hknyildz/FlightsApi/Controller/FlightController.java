package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Flight;
import com.hknyildz.FlightsApi.Service.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @GetMapping
    public @ResponseBody List<Flight> getAllFlights() {

        return flightService.getAllList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Flight createOrUpdate(@Valid @RequestBody FlightDto flightDto) {
        return flightService.createOrUpdate(flightDto);
    }

    @GetMapping("/arrival={airportCode}")
    public List<Flight> getByArrivalAirportCode(@PathVariable("airportCode") String airportCode) {
        return flightService.getFlightsByArrivalAirport(airportCode);
    }

    @GetMapping("/departure={airportCode}")
    public List<Flight> getByDepartureAirportCode(@PathVariable("airportCode") String airportCode) {
        return flightService.getFlightsByDepartureAirport(airportCode);
    }

    @RequestMapping(value = {"/{flightId}"}, method = RequestMethod.DELETE)
    public void deleteAirplane(@PathVariable("flightId") String flightId) {
        flightService.deleteById(flightId);
    }

}
