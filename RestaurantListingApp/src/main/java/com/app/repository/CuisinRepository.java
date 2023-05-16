package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Cuisin;

public interface CuisinRepository extends JpaRepository<Cuisin, Integer>{

	public Optional<Cuisin> findByCuisineName(String cuisineName);
}
