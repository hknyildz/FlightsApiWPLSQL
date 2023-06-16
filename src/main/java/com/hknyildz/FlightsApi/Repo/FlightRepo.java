package com.hknyildz.FlightsApi.Repo;

import com.hknyildz.FlightsApi.Exception.ApiRequestException;
import com.hknyildz.FlightsApi.Model.Dto.FlightDto;
import oracle.jdbc.OracleTypes;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class FlightRepo {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); // tarih biçimi belirlendi

    final static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    private final static String GET_ALL_FLIGHTS ="{? = call ROOT.GET_ALL_FLIGHTS()}";

    private final static String GET_FLIGHT_BY_ID ="{? = call ROOT.GET_FLIGHT_BY_ID()}";

    private final static String SAVE_FLIGHT ="{? = call ROOT.SAVE_FLIGHT(?,?,?,?,?,?,?)}";




    public List<FlightDto> findAll() throws ServiceException, SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_ALL_FLIGHTS);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            rs=(ResultSet) stmt.getObject(1);
            List<FlightDto> flights = new ArrayList<>();
            while (rs.next())
            {
                flights.add(buildFlightDtoObject(rs));
            }

            return flights;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    public Optional<FlightDto> findById(String id) throws ServiceException, SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_FLIGHT_BY_ID);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            rs=(ResultSet) stmt.getObject(1);

            while (rs.next())
            {
                return Optional.of(buildFlightDtoObject(rs));
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    public String save(FlightDto flightDto) throws SQLException {

        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(SAVE_FLIGHT);
            stmt.registerOutParameter(1, OracleTypes.VARCHAR);
            Date arrivalDate = dateFormat.parse(flightDto.getArrivalTime());
            Date departureDate = dateFormat.parse(flightDto.getDepartureTime());
            if ((flightDto.getId() != null)) {
                stmt.setInt(2, Integer.parseInt(flightDto.getId()));
            } else {
                stmt.setInt(2, OracleTypes.NULL);
            }

            stmt.setString(3,flightDto.getArrivalAirportCode());
            stmt.setString(4,flightDto.getDepartureAirportCode());
            stmt.setTimestamp(5,new Timestamp(arrivalDate.getTime()));
            stmt.setTimestamp(6,new Timestamp(departureDate.getTime()));
            stmt.setInt(7, Integer.parseInt(flightDto.getDuration()));
            stmt.setString(8,flightDto.getAirplaneCode());
            stmt.execute();
            String message =  stmt.getString(1);

            if(!message.equals("Success")){
                throw new ApiRequestException(message);
            }

            return message;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
            stmt.close();
        }
    }


    private FlightDto buildFlightDtoObject(ResultSet rs) throws SQLException {
        int duration = rs.getInt("DURATION");
        String durationString = (duration / 60) + " Hours : " + (duration % 60) + " Minutes";
        return new FlightDto(rs.getString("ID"), rs.getString("DEPARTURE_AIRPORT_CODE"), rs.getString("ARRIVAL_AIRPORT_CODE"), rs.getString("AIRPLANE_CODE"),rs.getTimestamp("DEPARTURE_TIME").toString(), rs.getTimestamp("ARRIVAL_TIME").toString(), durationString);
    }
}
