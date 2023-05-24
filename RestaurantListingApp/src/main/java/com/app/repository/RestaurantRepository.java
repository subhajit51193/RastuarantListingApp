package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

	public Optional<Restaurant> findByPhone(String phone);
	
}
