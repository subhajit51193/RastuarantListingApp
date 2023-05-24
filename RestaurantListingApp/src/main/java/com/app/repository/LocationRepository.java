package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

	public Optional<Location> findByCity(String city);
}
