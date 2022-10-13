package com.pluralsight.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pluralsight.model.Ride;
import com.pluralsight.repository.RideRepository;

@Service("rideService")
public class RideServiceImpl implements RideService {

	@Autowired
	private RideRepository rideRepository;
	
	
	@Override
	public Ride createRide(Ride ride) {
		return rideRepository.createRide(ride);
	}
	
	@Override
	public List<Ride> getRides() {
		return rideRepository.getRides();
	}
	
	@Override
	public Ride getRide(int id) {
		return rideRepository.getRide(id);
	}
	
	@Override
	public Ride updateRide(Ride ride) {
		return rideRepository.updateRide(ride);
	}

	@Override
	public void deleteRide(Integer id) {
		rideRepository.deleteRide(id);
	}

	@Override
	@Transactional
	public void batchUpdate() {
		List<Ride> rides = this.getRides();
		List<Object[]> pairs = new ArrayList<>();
		
		for(Ride r: rides) {
			Object[] pair = {LocalDateTime.now(), r.getId()};
			pairs.add(pair);
		}
		
		rideRepository.batchUpdate(pairs);
		
//		throw new DataAccessException("Test transactional") {};
									
	}

	
	
}
