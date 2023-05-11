package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Location;

public interface LocationRepository extends JpaRepository<Location, Integer>{

}
