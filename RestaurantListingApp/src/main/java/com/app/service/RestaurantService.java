package com.app.service;

import com.app.exception.RestaurantException;
import com.app.model.Restaurant;

public interface RestaurantService {

	public Restaurant addRestaurant(Restaurant restaurant)throws RestaurantException;
}
