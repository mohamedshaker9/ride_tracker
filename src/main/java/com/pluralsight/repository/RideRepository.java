package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideRepository {

	List<Ride> getRides();

	Ride createRide(Ride ride);
	
	
	Ride getRide(int id);

	Ride updateRide(Ride ride);

	void deleteRide(Integer id);

	void batchUpdate(List<Object[]> pairs);

}