package com.pluralsight.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

public class RideRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		Ride ride = new Ride();
		ride.setId(rs.getInt("id"));
		ride.setName(rs.getString("name"));
		ride.setDuration(rs.getInt("duration"));
		return ride;
	}

}
