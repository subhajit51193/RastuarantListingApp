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
		
		Set<Location> locations = restaurant.getLocations();
		Set<Cuisin> cuisins = restaurant.getCuisins();
		
		Optional<Restaurant> optr = restaurantRepository.findByPhone(restaurant.getPhone());
//		if no restaurant found
		if (optr.isEmpty()) {
			
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
			
			for (Cuisin cuisin: cuisins) {
				cuisin.setRestaurant(restaurant);
				restaurant.getCuisins().add(cuisin);
				cuisinRepository.save(cuisin);
			}
			return restaurantRepository.save(restaurant);
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
			return restaurantRepository.save(foundRestaurant);
		}
		
	
	}

	
}
