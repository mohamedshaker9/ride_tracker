package com.pluralsight.repository;

import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Ride createRide(Ride ride) {
		jdbcTemplate.update("INSERT INTO ride (name, duration) values (?, ?)", ride.getName(), ride.getDuration());
		return null;
	}
	
	@Override
	public Ride getRide(int id) {
		Ride ride = (Ride) jdbcTemplate.queryForObject("SELECT * FROM ride WHERE id = ?", new RideRowMapper(), id);
		return ride;
	}
	
	@Override
	public List<Ride> getRides() {
		List<Ride> rides = jdbcTemplate.query(
					"SELECT * from ride", 
					(ResultSet rs, int rowNum) -> {
					Ride ride = new Ride();
					ride.setId(rs.getInt("id"));
					ride.setName(rs.getString("name"));
					ride.setDuration(rs.getInt("duration"));
					return ride;
				});
		
		return rides;
	}
	
	@Override
	public Ride updateRide(Ride ride) {
		jdbcTemplate.update("UPDATE ride set name = ?, duration = ? WHERE id = ?", 
				ride.getName(), ride.getDuration(), ride.getId());
		return getRide(ride.getId());
	}
	
}
