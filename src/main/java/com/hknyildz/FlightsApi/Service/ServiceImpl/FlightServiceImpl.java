package com.hknyildz.FlightsApi.Service.ServiceImpl;

import com.hknyildz.FlightsApi.Exception.ApiRequestException;
import com.hknyildz.FlightsApi.Exception.EntityNotFoundException;
import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import com.hknyildz.FlightsApi.Model.Entity.Airport;
import com.hknyildz.FlightsApi.Model.Entity.Flight;
import com.hknyildz.FlightsApi.Repo.AirplaneRepo;
import com.hknyildz.FlightsApi.Repo.AirportRepo;
import com.hknyildz.FlightsApi.Repo.FlightRepo;
import com.hknyildz.FlightsApi.Repository.AirplaneRepository;
import com.hknyildz.FlightsApi.Repository.AirportRepository;
import com.hknyildz.FlightsApi.Repository.FlightRepository;
import com.hknyildz.FlightsApi.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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
    @Autowired
    FlightRepo flightRepo;

   @Autowired
    AirportRepo airportRepo;

  @Autowired
   AirplaneRepo airplaneRepo;

    @Override
    public List<FlightDto> getAllList() throws SQLException {
        List<FlightDto> flights = flightRepo.findAll();
        if (flights.isEmpty()) {
            throw new EntityNotFoundException();
        }
        return flights;
    }

    @Override
    public String createOrUpdate(FlightDto flightDto) {
        synchronized (getLock(flightDto.getDepartureAirportCode())) {
            try {
                getLock(flightDto.getArrivalAirportCode());
                FlightDto flight = new FlightDto();
                //checkBusinessRules(flight);
                dtoToEntity(flight, flightDto);
                return flightRepo.save(flight);

                /*
                flightRepository.save(flight);
                return flight;
                */

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                locks.remove(flightDto.getArrivalAirportCode());
                locks.remove(flightDto.getDepartureAirportCode());
            }
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

    private void dtoToEntity(FlightDto newFlight, FlightDto flightDto) {

        LocalDateTime arrivalDateTime = LocalDateTime.parse(flightDto.getArrivalTime(), formatter);
        LocalDateTime departureDateTime = LocalDateTime.parse(flightDto.getDepartureTime(), formatter);
        Long duration = ChronoUnit.MINUTES.between(departureDateTime, arrivalDateTime);

        newFlight.setArrivalAirportCode(flightDto.getArrivalAirportCode());
        newFlight.setDepartureAirportCode(flightDto.getDepartureAirportCode());
        newFlight.setArrivalTime(flightDto.getArrivalTime());
        newFlight.setDepartureTime(flightDto.getDepartureTime());
        newFlight.setDuration(duration.toString());
        newFlight.setAirplaneCode(flightDto.getAirplaneCode());
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


    private void checkValidations(AirplaneDto airplane, AirportDto arrivalAirport, AirportDto departureAirport) {

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
        int flightCount = flightRepository.getDailyFlightCountBetweenAirports(departureAirportCode, arrivalAirportCode, departureDateTime.toLocalDate());
        if (flightCount >= MAX_DAILY_FLIGHTS) {
            throw new ApiRequestException("There is daily at most 3 flights allowed for an airline between 2 destinations.");
        }
    }


}
