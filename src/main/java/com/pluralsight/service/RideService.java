package com.pluralsight.service;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideService {

	List<Ride> getRides();

	Ride createRide(Ride ride);
	
	Ride getRide(int id);

	Ride updateRide(Ride ride);

	void deleteRide(Integer id);

	void batchUpdate();

}