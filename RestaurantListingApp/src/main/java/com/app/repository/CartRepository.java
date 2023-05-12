package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

}
