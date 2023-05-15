package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	public Restaurant findByPhone(String phone);
	
}
