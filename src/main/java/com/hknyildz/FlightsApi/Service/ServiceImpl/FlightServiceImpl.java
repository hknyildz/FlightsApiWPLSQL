package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.ApiRequestException;
import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
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

    private final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm";
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AirplaneRepository airplaneRepository;

    @Autowired
    AirportRepository airportRepository;


    @Override
    @Async
    public List<Flight> getAllList() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        if (flights.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return flights;
    }

    @Override
    public synchronized Flight createOrUpdate(FlightDto flightDto) {
        Airplane airplane = airplaneRepository.findByAirplaneCode(flightDto.getAirplaneCode());
        if (airplane == null) {
            throw new EntityNotFoundException("There is no plane with code:" + flightDto.getAirplaneCode());
        }
        Airport arrivalAirport = airportRepository.findByAirportCode(flightDto.getArrivalAirportCode());
        Airport departureAirport = airportRepository.findByAirportCode(flightDto.getDepartureAirportCode());

        if (arrivalAirport == null || departureAirport == null) {
            throw new EntityNotFoundException("Airport Not Found");
        } else if (arrivalAirport.getAirportCode().equals(departureAirport.getAirportCode())) {
            throw new ApiRequestException("Airports cannot be same");
        }

        Optional<Flight> flight;
        if (flightDto.getId() != null) {
            flight = flightRepository.findById(flightDto.getId());
            flight.orElseThrow(() -> new EntityNotFoundException("Flight not found"));
        } else {
            flight = Optional.of(new Flight());
        }

        if (isPlaneLanded(airplane)) {
            throw new ApiRequestException("New entry cannot be made until airplane landed.");
        }

        if (!isEligibilForFlight(departureAirport.getAirportCode(), arrivalAirport.getAirportCode())) {
            throw new ApiRequestException("There is daily at most 3 flights allowed for an airline between 2 destinations.");
        }

        flight.get().setAirplane(airplane);
        flight.get().setArrivalAirport(arrivalAirport.getAirportCode());
        flight.get().setDepartureAirport(departureAirport.getAirportCode());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN);
        LocalDateTime arrivalDateTime = LocalDateTime.parse(flightDto.getArrivalTime(), formatter);
        LocalDateTime departureDateTime = LocalDateTime.parse(flightDto.getDepartureTime(), formatter);

        if (isPlaneHasFlight(airplane, arrivalDateTime, departureDateTime)) {
            throw new ApiRequestException("Plane has flight between dates: " + departureDateTime + " and " + departureDateTime);
        }

        flight.get().setArrivalTime(arrivalDateTime);
        flight.get().setDepartureTime(departureDateTime);
        Long durationMinutes = ChronoUnit.MINUTES.between(departureDateTime, arrivalDateTime);
        String duration = ((int) (durationMinutes / 60)) + " Hours : " + ((int) (durationMinutes % 60)) + " Minutes";
        flight.get().setDuration(duration);

        return flightRepository.save(flight.get());
    }

    private boolean isPlaneHasFlight(Airplane airplane, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {

        for (Flight flight : airplane.getFlights()) {
            if ((flight.getDepartureTime().isAfter(departureDateTime) && flight.getDepartureTime().isBefore(arrivalDateTime)) || (flight.getArrivalTime().isAfter(departureDateTime) && flight.getArrivalTime().isBefore(arrivalDateTime))) {
                return true;
            }
        }
        return false;
    }

    private boolean isEligibilForFlight(String departureAirportCode, String arrivalAirportCode) {
        int flightCount = flightRepository.getDailyFlightCountBetweenAirports(departureAirportCode, arrivalAirportCode);

        return flightCount < MAX_DAILY_FLIGHTS;
    }

    private boolean isPlaneLanded(Airplane airplane) {
        return airplane.getFlights().stream().anyMatch(flight -> flight.getDepartureTime().isBefore(LocalDateTime.now()) && flight.getArrivalTime().isAfter(LocalDateTime.now()));
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
