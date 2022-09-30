package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	
	
	
	@Test(timeout=3000)
	public void testCreateRides() {
		RestTemplate restTemplate = new RestTemplate();

		Ride ride = new Ride();
		
		ride.setName("Luxor");
		ride.setDuration(50);
		restTemplate.postForObject("http://localhost:8080/ride_tracker/ride", ride, Ride.class);
	}
	
	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
	
	@Test(timeout=3000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/2", Ride.class);
		
		System.out.println("Ride name: " + ride.getName());
	
	}
	
	@Test(timeout=3000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		System.out.println(" Before: Ride name: " + ride.getName());
		ride.setName("Abu hammad");
		restTemplate.put("http://localhost:8080/ride_tracker/ride", ride, Ride.class);
		
		Ride ride2 = restTemplate.getForObject("http://localhost:8080/ride_tracker/ride/1", Ride.class);
		System.out.println(" After: Ride name: " + ride.getName());
	}
	
}
