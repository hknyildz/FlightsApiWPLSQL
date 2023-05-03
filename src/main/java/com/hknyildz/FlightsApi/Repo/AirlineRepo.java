package com.hknyildz.FlightsApi.Repo;

import com.hknyildz.FlightsApi.Model.Dto.AirlineDto;
import com.hknyildz.FlightsApi.Model.Entity.Airline;
import oracle.jdbc.OracleTypes;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Component;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class AirlineRepo  {

    private final static String url = "jdbc:oracle:thin:@localhost:1521/orcl";
    private final static String GET_ALL_AIRLINES ="{? = call ROOT.GETALLAIRLINES()}";
    private final static String GET_AIRLINE_BY_AIRLINECODE ="{call ? := ROOT.GET_AIRLINE_BY_AIRLINECODE(?)}";


    public List<Airline> findAll() throws ServiceException, SQLException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_ALL_AIRLINES);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            rs=(ResultSet) stmt.getObject(1);
            List<Airline> airlines = new ArrayList<>();
            while (rs.next())
            {
                airlines.add(buildAirlineObject(rs));
            }

            return airlines;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            conn.close();
            stmt.close();
            rs.close();
        }
    }

    public Airline findAirlineByAirlineCode(String airlineCode) throws SQLException,ServiceException {
        Connection conn = null;
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url,"root","1234");
            stmt = conn.prepareCall(GET_AIRLINE_BY_AIRLINECODE);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.setString(2,airlineCode);
            stmt.execute();
            rs = (ResultSet) stmt.getObject(1);

            while(rs.next()) {
                return buildAirlineObject(rs);
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

    private Airline buildAirlineObject(ResultSet rs) throws SQLException {
        Airline airline = new Airline();
        airline.setId(rs.getString("ID"));
        airline.setAirlineCode(rs.getString("AIRLINE_CODE"));
        airline.setName(rs.getString("NAME"));
        return airline;
    }
}
