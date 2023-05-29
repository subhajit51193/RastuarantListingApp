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
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		
		Set<Location> locations = restaurant.getLocations();
		Set<Cuisin> cuisins = restaurant.getCuisins();
		
		Optional<Restaurant> optr = restaurantRepository.findByPhone(restaurant.getPhone());
//		if no restaurant found
		if (optr.isEmpty()) {
			
			Restaurant newRestaurant = new Restaurant();
			newRestaurant.setName(restaurant.getName());
			newRestaurant.setAddress(restaurant.getAddress());
			newRestaurant.setPhone(restaurant.getPhone());
			for (Location location: locations) {
				Optional<Location> optl = locationRepository.findByCity(location.getCity());
				if (optl.isEmpty()) {
					location.getRestaurants().add(newRestaurant);
					newRestaurant.getLocations().add(location);
					locationRepository.save(location);
				}
				else {
					Location foundLocation = optl.get();
					foundLocation.getRestaurants().add(newRestaurant);
					newRestaurant.getLocations().add(foundLocation);
					locationRepository.save(foundLocation);
				}
				
			}
			
			for (Cuisin cuisin: cuisins) {
				cuisin.setRestaurant(newRestaurant);
				newRestaurant.getCuisins().add(cuisin);
				cuisinRepository.save(cuisin);
			}
			
			Restaurant addedRestaurant = restaurantRepository.save(newRestaurant);
			if (addedRestaurant != null) {
				return addedRestaurant;
			}
			else {
				throw new RestaurantException("Error");
			}
		}
		else {
			Restaurant foundRestaurant = optr.get();
			foundRestaurant.setName(restaurant.getName());
			foundRestaurant.setAddress(restaurant.getAddress());
			for (Location location: locations) {
				Optional<Location> optl = locationRepository.findByCity(location.getCity());
				if (optl.isEmpty()) {
					location.getRestaurants().add(foundRestaurant);
					foundRestaurant.getLocations().add(location);
					locationRepository.save(location);
				}
				else {
					Location foundLocation = optl.get();
					foundLocation.getRestaurants().add(foundRestaurant);
					foundRestaurant.getLocations().add(foundLocation);
					locationRepository.save(foundLocation);
				}
			}
			
			for (Cuisin cuisin: cuisins) {
				cuisin.setRestaurant(foundRestaurant);
				foundRestaurant.getCuisins().add(cuisin);
				cuisinRepository.save(cuisin);
			}
			Restaurant updatedRestaurant = restaurantRepository.save(foundRestaurant);
			if (updatedRestaurant != null) {
				return updatedRestaurant;
			}
			else {
				throw new RestaurantException("Error");
			}
		}
		
	
	}

	
}
