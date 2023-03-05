package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Model.Entity.Flight;
import com.hknyildz.FlightsApi.Repository.AirplaneRepository;
import com.hknyildz.FlightsApi.Repository.AirportRepository;
import com.hknyildz.FlightsApi.Repository.FlightRepository;
import com.hknyildz.FlightsApi.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class FlightServiceImpl implements FlightService {

    private final int MAX_DAILY_FLIGHTS = 3;
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirplaneRepository airplaneRepository;

    @Autowired
    AirportRepository airportRepository;


    @Override
    @Async
    public List<Flight> getAllList() {
        return (List<Flight>) flightRepository.findAll();
    }

    @Override
    public synchronized Flight createOrUpdate(FlightDto flightDto) {
        Airplane airplane = airplaneRepository.findByAirplaneCode(flightDto.getAirplaneCode());
        if (airplane == null) {
            throw new IllegalStateException("There is no plane with code:" + flightDto.getAirplaneCode());
        }
        Airport arrivalAirport = airportRepository.findByAirportCode(flightDto.getArrivalAirportCode());
        Airport departureAirport = airportRepository.findByAirportCode(flightDto.getDepartureAirportCode());

        if (arrivalAirport == null || departureAirport == null) {
            throw new IllegalArgumentException("Airport Not Found");
        }

        Optional<Flight> flight;
        if (flightDto.getId() != null) {
            flight = flightRepository.findById(flightDto.getId());
            flight.orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        } else {
            flight = Optional.of(new Flight());
        }

        if (!isPlaneLanded(airplane)) {
            throw new IllegalStateException("New entry cannot be made until airplane landed.");
        }


        if (!isEligibilForFlight(departureAirport.getAirportCode(), arrivalAirport.getAirportCode())) {
            throw new IllegalArgumentException("There is daily at most 3 flights allowed for an airline between 2 destinations.");
        }

        flight.get().setAirplane(airplane);
        flight.get().setArrivalAirport(arrivalAirport.getAirportCode());
        flight.get().setDepartureAirport(departureAirport.getAirportCode());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime arrivalDateTime = LocalDateTime.parse(flightDto.getArrivalDateTime(), formatter);
        LocalDateTime departureDateTime = LocalDateTime.parse(flightDto.getDepartureTime(),formatter);

        flight.get().setArrivalTime(arrivalDateTime);
        flight.get().setDepartureTime(departureDateTime);
        flight.get().setDuration(ChronoUnit.MINUTES.between(departureDateTime, arrivalDateTime)+"Minutes");

        return flightRepository.save(flight.get());
    }

    private boolean isEligibilForFlight(String departureAirportCode, String arrivalAirportCode) {
        int flightCount = flightRepository.getDailyFlightCountBetweenAirports(departureAirportCode, arrivalAirportCode);

        return flightCount <= MAX_DAILY_FLIGHTS;
    }

    private boolean isPlaneLanded(Airplane airplane) {
        return airplane.getFlights()
                .stream()
                .anyMatch(flight -> flight.getDepartureTime().isBefore(LocalDateTime.now()) && flight.getArrivalTime().isAfter(LocalDateTime.now()));
    }

    @Override
    public List<Flight> getFlightsByDepartureAirport(String airportCode) {

        return flightRepository.findByDepartureAirportCode(airportCode);
    }

    @Override
    public List<Flight> getFlightsByArrivalAirport(String airportCode) {
        return flightRepository.findByArrivalAirportCode(airportCode);
    }

    @Override
    public void deleteById(String flightId) {
        flightRepository.deleteById(flightId);
    }
}
