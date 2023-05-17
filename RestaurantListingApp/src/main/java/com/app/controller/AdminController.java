package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.exception.CuisinException;
import com.app.exception.RestaurantException;
import com.app.model.Cuisin;
import com.app.model.Restaurant;
import com.app.service.CuisinService;
import com.app.service.RestaurantService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private CuisinService cuisinService;
	
	@PostMapping("/addRestaurant")
	public ResponseEntity<Restaurant> addRestaurantHandler(@RequestBody Restaurant restaurant) throws RestaurantException{
		Restaurant newRestaurant = restaurantService.addRestaurant(restaurant);
		return new ResponseEntity<Restaurant>(newRestaurant,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/addCuisin/{id}")
	public ResponseEntity<Cuisin> addCuisinHandler(@RequestBody Cuisin cuisin, @PathVariable("id") Integer restaurantId) throws RestaurantException, CuisinException{
		Cuisin newCuisin = cuisinService.addCuisin(restaurantId, cuisin);
		return new ResponseEntity<Cuisin>(newCuisin,HttpStatus.ACCEPTED);
	}
}
