package com.app.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer custId;
	private String firstName;
	private String lastName;
	
	@Column(unique = true)
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String address;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",fetch=FetchType.EAGER)
	private List<Authority> authorities = new ArrayList<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",fetch=FetchType.EAGER)
	private Set<Order> orders = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer",fetch=FetchType.EAGER)
	private Set<Review> reviews = new HashSet<>();
	/*
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
            name = "customers_restaurants",
            joinColumns = @JoinColumn(
                    name = "cust_id", referencedColumnName = "custId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "restaurant_id", referencedColumnName = "restaurantId"
            )
    )
	private Set<Restaurant> restaurants = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
            name = "users_cuisins",
            joinColumns = @JoinColumn(
                    name = "cust_id", referencedColumnName = "custId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "cuisin_id", referencedColumnName = "cuisineId"
            )
    )
	private Set<Cuisin> cuisines = new HashSet<>();
	
	@ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    }, fetch = FetchType.EAGER,
    mappedBy = "customers")
	private Set<Location> locations = new HashSet<>();
	*/
}
