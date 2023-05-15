package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.RestaurantException;
import com.app.model.Restaurant;
import com.app.service.RestaurantService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RestaurantService restaurantService;
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurantHandler(@RequestBody Restaurant restaurant) throws RestaurantException{
		Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
		return new ResponseEntity<Restaurant>(newRestaurant,HttpStatus.ACCEPTED);
	}
}
