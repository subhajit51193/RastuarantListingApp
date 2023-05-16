package com.app.service;

import com.app.exception.RestaurantException;
import com.app.model.Cuisin;

public interface CuisinService {

	public Cuisin addCuisin(Integer restaurantId,Cuisin cuisin) throws RestaurantException;
}
