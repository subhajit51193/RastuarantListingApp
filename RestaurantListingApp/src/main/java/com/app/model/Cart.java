package com.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;
	
	@ManyToOne
	@JoinColumn(name = "restaurant_id")
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn(name = "cuisine_id")
	private Cuisin cuisin;
	
	private Long quantity;
}
