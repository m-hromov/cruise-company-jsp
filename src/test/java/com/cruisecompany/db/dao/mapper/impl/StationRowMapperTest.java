package com.cruisecompany.db.dao.mapper.impl;

import com.cruisecompany.db.Columns;
import com.cruisecompany.entity.Station;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StationRowMapperTest {

    @Test
    void testStationRowMapping() {
        Station station = null;
        try (ResultSet rs = Mockito.mock(ResultSet.class)) {
            Mockito.when(rs.getLong(Columns.STATION_ID)).thenReturn(10L);
            Mockito.when(rs.getString(Columns.STATION_CITY)).thenReturn("Odesa");
            Mockito.when(rs.getString(Columns.STATION_COUNTRY)).thenReturn("Ukraine");
            StationRowMapper srm = new StationRowMapper();
            station = srm.map(null, rs);
        } catch (SQLException e) {
            fail(e);
        }
        assertEquals(station.getId(), 10L);
        assertEquals(station.getCity(), "Odesa");
        assertEquals(station.getCountry(), "Ukraine");
    }
}