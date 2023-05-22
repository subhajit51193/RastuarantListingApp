package com.app.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.RestaurantException;
import com.app.model.Cuisin;
import com.app.model.Location;
import com.app.model.Restaurant;
import com.app.repository.CuisinRepository;
import com.app.repository.LocationRepository;
import com.app.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private CuisinRepository cuisinRepository;
	
	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		
		Set<Location> locations = restaurant.getLocations();
		for (Location location: locations) {
			location.getRestaurants().add(restaurant);
			locationRepository.save(location);
			restaurant.getLocations().add(location);
		}
		Set<Cuisin> cuisins = restaurant.getCuisins();
		for (Cuisin cuisin : cuisins) {
			cuisin.getRestaurants().add(restaurant);
			cuisinRepository.save(cuisin);
			restaurant.getCuisins().add(cuisin);
		}
//		
//		for (Location location: locations) {
//			for (Cuisin cuisin: cuisins) {
//				location.getRestaurants().add(restaurant);
//				location.getCuisins().add(cuisin);
//				cuisin.getLocations().add(location);
//				cuisin.getRestaurants().add(restaurant);
//				restaurant.getLocations().add(location);
//				restaurant.getCuisins().add(cuisin);
//				locationRepository.save(location);
//				cuisinRepository.save(cuisin);
//			}
//		}
		return restaurantRepository.save(restaurant);
		

		
	}

	
}
