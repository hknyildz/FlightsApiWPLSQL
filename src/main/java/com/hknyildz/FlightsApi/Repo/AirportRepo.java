package com.hknyildz.FlightsApi.Repo;

import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Dto.AirportDto;
import oracle.jdbc.OracleTypes;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AirportRepo {

    final static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    private final static String GET_ALL_AIRPORTS ="{? = call ROOT.GET_ALL_AIRPORTS()}";

    private final static String GET_AIRPORT_BY_AIRPORT_CODE ="{? = call ROOT.GET_AIRPORT_BY_AIRPORT_CODE(?)}";

    public List<AirportDto> findAll() throws ServiceException, SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_ALL_AIRPORTS);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            rs=(ResultSet) stmt.getObject(1);
            List<AirportDto> airports = new ArrayList<>();
            while (rs.next())
            {
                airports.add(buildAirportObject(rs));
            }

            return airports;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    public AirportDto findByAirportCode(String airportCode) throws SQLException,ServiceException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_AIRPORT_BY_AIRPORT_CODE);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2,airportCode);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            while(rs.next()) {
                return buildAirportObject(rs);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    private AirportDto buildAirportObject(ResultSet rs) throws SQLException {
        AirportDto airportDto = new AirportDto();
        airportDto.setAirportCode(rs.getString("AIRPORT_CODE"));
        airportDto.setName(rs.getString("NAME"));
        return airportDto;
    }
}
