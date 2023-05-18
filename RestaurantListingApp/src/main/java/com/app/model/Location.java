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
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer locationId;
	private String city;
	private String state;
	private String country;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
            name = "locations_restaurants",
            joinColumns = @JoinColumn(
                    name = "location_id", referencedColumnName = "locationId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "restaurant_id", referencedColumnName = "restaurantId"
            )
    )
	private Set<Restaurant> restaurants = new HashSet<>();
	/*
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
            name = "locations_customers",
            joinColumns = @JoinColumn(
                    name = "location_id", referencedColumnName = "locationId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cust_id", referencedColumnName = "custId"
            )
    )
	private Set<Customer> customers = new HashSet<>();
	*/
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
            name = "locations_cuisins",
            joinColumns = @JoinColumn(
                    name = "location_id", referencedColumnName = "locationId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cuisine_id", referencedColumnName = "cuisineId"
            )
    )
	private Set<Cuisin> cuisins = new HashSet<>();
}
