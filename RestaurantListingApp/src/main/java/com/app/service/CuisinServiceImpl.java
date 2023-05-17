package com.app.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.exception.CuisinException;
import com.app.exception.RestaurantException;
import com.app.model.Cuisin;
import com.app.model.Restaurant;
import com.app.repository.CuisinRepository;
import com.app.repository.RestaurantRepository;

@Service
public class CuisinServiceImpl implements CuisinService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private CuisinRepository cuisinRepository;
	
	@Override
	public Cuisin addCuisin(Integer restaurantId, Cuisin cuisin) throws RestaurantException, CuisinException {
		
		Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
		if (opt.isEmpty()) {
			throw new RestaurantException("Not found");
		}
		else {
			Restaurant restaurant = opt.get();
			
				Set<Cuisin> existingCuisins = restaurant.getCuisins();
				if (existingCuisins.contains(cuisin)) {
					throw new CuisinException("Already exists");
				}
				else {
					restaurant.getCuisins().add(cuisin);
					cuisin.getRestaurants().add(restaurant);
					restaurantRepository.save(restaurant);
					return cuisinRepository.save(cuisin);
				}
				
			
			
		}
	}

	

	
}
