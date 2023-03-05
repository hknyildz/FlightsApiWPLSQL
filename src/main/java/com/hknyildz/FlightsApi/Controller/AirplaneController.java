package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("airplanes")
public class AirplaneController {

    @Autowired
    AirplaneService airplaneService;

    @GetMapping
    public @ResponseBody List<Airplane> getAllAirplanes() {

        return (List<Airplane>) airplaneService.getAllList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Airplane createOrUpdate(@RequestBody AirplaneDto airplaneDto) {
        return airplaneService.createOrUpdate(airplaneDto);
    }

    @GetMapping("/{airplaneCode}")
    public Airplane getByAirplaneCode(@PathVariable("airplaneCode") String airplaneCode) {
        return airplaneService.getByAirplaneCode(airplaneCode);
    }

    @GetMapping("/airline={airlineCode}")
    public List<Airplane> getByAirlineCode(@PathVariable("airlineCode") String airlineCode)
    {
        return airplaneService.getByAirlineCode(airlineCode);
    }

    @RequestMapping(value = {"/{airplaneCode}"},method = RequestMethod.DELETE)
    public void deleteAirplane(@PathVariable("airplaneCode")String airplaneCode)
    {
        airplaneService.removeByAirplaneCode(airplaneCode);
    }

}
