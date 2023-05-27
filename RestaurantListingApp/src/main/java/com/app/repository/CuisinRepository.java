package com.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Cuisin;
import com.app.model.Restaurant;

public interface CuisinRepository extends JpaRepository<Cuisin, Integer>{

	public List<Cuisin> findByCuisineName(String cuisineName);
	
	@Query("select restaurant from Cuisin where cuisineId=?1")
	public Restaurant getRestaurantFromCuisine(Integer cuisineId);
}
