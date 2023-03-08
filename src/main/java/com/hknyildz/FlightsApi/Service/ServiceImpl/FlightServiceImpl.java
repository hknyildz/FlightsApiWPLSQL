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
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FlightServiceImpl implements FlightService {

    private final int MAX_DAILY_FLIGHTS = 3;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private ConcurrentHashMap<String, Object> locks = new ConcurrentHashMap<>();

    @Autowired
    FlightRepository flightRepository;
    @Autowired
    AirplaneRepository airplaneRepository;
    @Autowired
    AirportRepository airportRepository;

    @Override
    public List<FlightDto> getAllList() {
        List<Flight> flights = (List<Flight>) flightRepository.findAll();
        if (flights.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return entityListToDtoList(flights);
    }

    @Override
    public Flight createOrUpdate(FlightDto flightDto) {
        synchronized (getLock(flightDto.getDepartureAirportCode())) {
            getLock(flightDto.getDepartureAirportCode());

            Airport arrivalAirport = airportRepository.findByAirportCode(flightDto.getArrivalAirportCode());
            Airport departureAirport = airportRepository.findByAirportCode(flightDto.getDepartureAirportCode());
            Airplane airplane = airplaneRepository.findByAirplaneCode(flightDto.getAirplaneCode());
            Flight flight;
            checkValidations(airplane, arrivalAirport, departureAirport);

            if (flightDto.getId() != null) {
                flight = flightRepository.findById(flightDto.getId()).orElseThrow(() -> new EntityNotFoundException("Flight not found"));
            } else {
                flight = new Flight();
            }
            dtoToEntity(flight, flightDto, airplane);
            checkBusinessRules(flight);
            flightRepository.save(flight);
            locks.remove(flightDto.getDepartureAirportCode());
            locks.remove(flightDto.getDepartureAirportCode());
            return flight;
        }
    }

    @Override
    public List<FlightDto> getFlightsByDepartureAirport(String airportCode) {

        return entityListToDtoList(flightRepository.findByDepartureAirportCode(airportCode));
    }

    @Override
    public List<FlightDto> getFlightsByArrivalAirport(String airportCode) {
        return entityListToDtoList(flightRepository.findByArrivalAirportCode(airportCode));
    }

    @Override
    public void deleteById(String flightId) {
        flightRepository.deleteById(flightId);
    }

    private Object getLock(String code) {
        locks.putIfAbsent(code, new Object());
        return locks.get(code);
    }


    private void checkBusinessRules(Flight flight) {
        checkPlaneHasFlight(flight.getAirplane(), flight.getArrivalTime(), flight.getDepartureTime());
        checkEligibilityForFlight(flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getDepartureTime());
    }

    private void dtoToEntity(Flight flight, FlightDto flightDto, Airplane airplane) {

        LocalDateTime arrivalDateTime = LocalDateTime.parse(flightDto.getArrivalTime(), formatter);
        LocalDateTime departureDateTime = LocalDateTime.parse(flightDto.getDepartureTime(), formatter);
        Long duration = ChronoUnit.MINUTES.between(departureDateTime, arrivalDateTime);

        flight.setAirplane(airplane);
        flight.setArrivalAirport(flightDto.getArrivalAirportCode());
        flight.setDepartureAirport(flightDto.getArrivalAirportCode());
        flight.setArrivalTime(arrivalDateTime);
        flight.setDepartureTime(departureDateTime);
        flight.setDuration(duration);
    }

    private FlightDto entityToDto(Flight flight) {
        String durationString = ((int) (flight.getDuration() / 60)) + " Hours : " + ((int) (flight.getDuration() % 60)) + " Minutes";
        return new FlightDto(flight.getId(), flight.getDepartureAirport(), flight.getArrivalAirport(), flight.getAirplane().getAirplaneCode(), flight.getDepartureTime().format(formatter), flight.getArrivalTime().format(formatter), durationString);

    }

    private List<FlightDto> entityListToDtoList(List<Flight> flights) {
        List<FlightDto> flightDtoList = new ArrayList<>();
        for (Flight flight : flights) {
            flightDtoList.add(entityToDto(flight));
        }
        return flightDtoList;
    }


    private void checkValidations(Airplane airplane, Airport arrivalAirport, Airport departureAirport) {

        if (airplane == null) {
            throw new EntityNotFoundException("There is no plane with given code");
        }

        if (arrivalAirport == null || departureAirport == null) {
            throw new EntityNotFoundException("Airport Not Found");
        }

        if (arrivalAirport.getAirportCode().equals(departureAirport.getAirportCode())) {
            throw new ApiRequestException("Airports cannot be same");
        }
    }

    private void checkPlaneHasFlight(Airplane airplane, LocalDateTime arrivalDateTime, LocalDateTime departureDateTime) {

        for (Flight flight : airplane.getFlights()) {
            if (flight.getDepartureTime().isEqual(departureDateTime) || (departureDateTime.isAfter(flight.getDepartureTime()) && departureDateTime.isBefore(flight.getArrivalTime())) || (arrivalDateTime.isAfter(flight.getDepartureTime()) && arrivalDateTime.isBefore(flight.getArrivalTime()))) {
                throw new ApiRequestException("Plane has flight between dates: " + flight.getDepartureTime() + " and " + flight.getArrivalTime());
            }
        }
    }

    private void checkEligibilityForFlight(String departureAirportCode, String arrivalAirportCode, LocalDateTime departureDateTime) {
        int flightCount = flightRepository.getDailyFlightCountBetweenAirports(departureAirportCode, arrivalAirportCode, departureDateTime.toLocalDate()); // gunluk hesaplama eksik
        if (flightCount >= MAX_DAILY_FLIGHTS) {
            throw new ApiRequestException("There is daily at most 3 flights allowed for an airline between 2 destinations.");
        }
    }


}
