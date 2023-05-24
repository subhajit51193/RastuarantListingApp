package com.app.service;

import java.util.Optional;
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
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {//not working need to check
		
		
		Optional<Restaurant> optr = restaurantRepository.findByPhone(restaurant.getPhone());
		if (optr.isEmpty()) {
			Set<Location> locations = restaurant.getLocations();
			for (Location location: locations) {
				Optional<Location> optl = locationRepository.findByCity(location.getCity());
				if (optl.isEmpty()) {
					location.getRestaurants().add(restaurant);
					restaurant.getLocations().add(location);
					locationRepository.save(location);
				}
				else {
					Location foundLocation = optl.get();
					foundLocation.getRestaurants().add(restaurant);
					restaurant.getLocations().add(foundLocation);
					locationRepository.save(foundLocation);
				}
				
			}
			Set<Cuisin> cuisins = restaurant.getCuisins();
			for (Cuisin cuisin : cuisins) {
				cuisin.setRestaurant(restaurant);
			}
			
			return restaurantRepository.save(restaurant);
		}
		else {
			Restaurant foundRestaurant = optr.get();
			Set<Location> locations = foundRestaurant.getLocations();
			for (Location location: locations) {
				Optional<Location> optl = locationRepository.findByCity(location.getCity());
				if (optl.isEmpty()) {
					location.getRestaurants().add(foundRestaurant);
					locationRepository.save(location);
				}
				else {
					Location foundLocation = optl.get();
					foundLocation.getRestaurants().add(foundRestaurant);
					locationRepository.save(foundLocation);
				}
				
			}
			Set<Cuisin> cuisins = foundRestaurant.getCuisins();
			for (Cuisin cuisin : cuisins) {
				cuisin.setRestaurant(foundRestaurant);
			}
			return restaurantRepository.save(foundRestaurant);
		}
		
	
	}

	
}
