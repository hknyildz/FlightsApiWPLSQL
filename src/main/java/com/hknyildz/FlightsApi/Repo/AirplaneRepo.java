package com.hknyildz.FlightsApi.Repo;

import com.hknyildz.FlightsApi.Model.Dto.AirplaneDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import com.hknyildz.FlightsApi.Model.Entity.Airplane;
import oracle.jdbc.OracleTypes;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AirplaneRepo {

    final static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    private final static String GET_ALL_AIRPLANES ="{? = call ROOT.GET_ALL_AIRPLANES()}";

    private final static String GET_AIRPLANE_BY_AIRPLANECODE ="{? = call ROOT.GET_AIRPLANE_BY_AIRPLANECODE(?)}";

    private final static String GET_AIRPLANE_BY_AIRLINECODE ="{? = call ROOT.GET_AIRPLANE_BY_AIRLINECODE(?)}";


    public List<AirplaneDto> findAll() throws ServiceException, SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_ALL_AIRPLANES);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            rs=(ResultSet) stmt.getObject(1);
            List<AirplaneDto> airplanes = new ArrayList<>();
            while (rs.next())
            {
                airplanes.add(buildAirplaneObject(rs));
            }

            return airplanes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    public AirplaneDto findByAirplaneCode(String airplaneCode) throws SQLException,ServiceException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_AIRPLANE_BY_AIRPLANECODE);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2,airplaneCode);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while(rs.next()) {
                return buildAirplaneObject(rs);
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

    public List<AirplaneDto> findByAirlineCode(String airlineCode) throws SQLException,ServiceException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_AIRPLANE_BY_AIRLINECODE);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2,airlineCode);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);
            List<AirplaneDto> airplanes = new ArrayList<>();
            while(rs.next()) {
                airplanes.add(buildAirplaneObject(rs));
            }
            return airplanes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }


    private AirplaneDto buildAirplaneObject(ResultSet rs) throws SQLException {
        AirplaneDto airplane = new AirplaneDto();
        airplane.setAirplaneCode(rs.getString("AIRPLANE_CODE"));
        airplane.setAirlineCode(rs.getString("AIRLINE_CODE"));
        return airplane;
    }
}
