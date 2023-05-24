package com.app.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Cuisin;

public interface CuisinRepository extends JpaRepository<Cuisin, Integer>{

	public List<Cuisin> findByCuisineName(String cuisineName);
}
