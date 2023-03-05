package com.hknyildz.FlightsApi.Controller;

import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("airplanes")
public class AirplaneController {

    @Autowired
    AirplaneRepository airplaneRepository;

    @GetMapping
    public @ResponseBody List<Airplane> getAllAirplanes() {

        return (List<Airplane>) airplaneRepository.findAll();
    }

}
