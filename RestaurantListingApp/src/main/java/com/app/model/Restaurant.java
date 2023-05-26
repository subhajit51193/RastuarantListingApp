package com.app.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer restaurantId;
	private String name;
	private String address;
	private String phone;
	private Integer rating;
	
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "restaurants")
	private Set<Location> locations = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant",fetch=FetchType.EAGER)
	private Set<Cuisin> cuisins = new HashSet<>();
	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant",fetch=FetchType.EAGER)
	private Set<Review> reviews = new HashSet<>();
	
	/*
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "restaurants")
	private Set<Customer> customers = new HashSet<>();
	*/
	
}
