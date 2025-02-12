package com.pluralsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;
import com.pluralsight.util.ServiceError;

@Controller
public class RideController {

	@Autowired
	private RideService rideService;
	
	@RequestMapping(value = "/ride", method = RequestMethod.POST)
	public @ResponseBody Ride createRide(@RequestBody Ride ride) {
		return rideService.createRide(ride);
	}
	
	@RequestMapping(value = "/rides", method = RequestMethod.GET)
	public @ResponseBody List<Ride> getRides() {
		return rideService.getRides();
	}
	
	@RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
	public @ResponseBody Ride getRide(@PathVariable(value="id") Integer id) {
		return rideService.getRide(id);
	}
	
	@RequestMapping(value = "/ride", method = RequestMethod.PUT)
	public @ResponseBody Ride updatRide(@RequestBody Ride ride) {
		return rideService.updateRide(ride);
	}
	
	
	@RequestMapping(value = "/ride/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteRide(@PathVariable(value="id") Integer id) {
		 rideService.deleteRide(id);
		 return null;
	}
	
	@RequestMapping(value = "/batch", method = RequestMethod.GET)
	public @ResponseBody Object getRide() {
		
		rideService.batchUpdate();
		return null;
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public @ResponseBody Object test() {
		throw new DataAccessException("Test Handling Excpetion") {};
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ServiceError> handleException(RuntimeException ex) {
		ServiceError serviceError = new ServiceError(HttpStatus.OK.value(), ex.getMessage());
		return new ResponseEntity<>(serviceError, HttpStatus.OK);
	}
	
}
