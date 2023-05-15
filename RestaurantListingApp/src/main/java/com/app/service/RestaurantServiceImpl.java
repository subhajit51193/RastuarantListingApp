package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.RestaurantException;
import com.app.model.Location;
import com.app.model.Restaurant;
import com.app.repository.LocationRepository;
import com.app.repository.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public Restaurant addRestaurant(Restaurant restaurant) throws RestaurantException {
		
//		Restaurant newRestaurant =  new Restaurant();
		if (restaurantRepository.findByPhone(restaurant.getPhone()) != null) {
			throw new RestaurantException("Already exists");
		}
		else {
//			return restaurantRepository.save(restaurant);
			Location newLocation = restaurant.getLocation();
			newLocation.getRestaurants().add(restaurant);
			restaurant.setLocation(newLocation);
			locationRepository.save(newLocation);
			return restaurantRepository.save(restaurant);
		}
		
	}

	
}
