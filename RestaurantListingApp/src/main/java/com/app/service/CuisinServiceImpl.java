package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public Cuisin addCuisin(Integer restaurantId, Cuisin cuisin) throws RestaurantException {
		
		Optional<Restaurant> opt = restaurantRepository.findById(restaurantId);
		if (opt.isEmpty()) {
			throw new RestaurantException("Not found");
		}
		else {
			Restaurant restaurant = opt.get();
			
			if (cuisinRepository.findByCuisineName(cuisin.getCuisineName()).isEmpty()) {
				restaurant.getCuisins().add(cuisin);
				cuisin.getRestaurants().add(restaurant);
				restaurantRepository.save(restaurant);
				return cuisinRepository.save(cuisin);
			}
			else {
				Cuisin updateCuisin = cuisinRepository.findByCuisineName(cuisin.getCuisineName()).get();
				restaurant.getCuisins().add(updateCuisin);
				cuisin.getRestaurants().add(restaurant);
				restaurantRepository.save(restaurant);
				return cuisinRepository.save(updateCuisin);
			}
			
		}
	}

	

	
}
