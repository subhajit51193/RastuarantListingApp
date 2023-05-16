package com.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Cart;
import com.app.model.Cuisin;

public interface CartRepository extends JpaRepository<Cart, Integer>{

	
}
