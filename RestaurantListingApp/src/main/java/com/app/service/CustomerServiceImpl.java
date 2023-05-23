package com.app.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.CustomerException;
import com.app.model.Authority;
import com.app.model.Cart;
import com.app.model.Customer;
import com.app.repository.CuisinRepository;
import com.app.repository.CustomerRepository;
import com.app.repository.LocationRepository;
import com.app.repository.RestaurantRepository;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	---------------------------------------
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private CuisinRepository cuisinRepository;
	
	
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		List<Authority> authorities= customer.getAuthorities();
		
		for(Authority authority:authorities) {
			authority.setCustomer(customer);
		}
		
		return customerRepository.save(customer);
		
		
	}

	@Override
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException {
		
		return customerRepository.findByEmail(email).orElseThrow(() -> new CustomerException("Customer Not found with Email: "+email));
	}

	@Override
	public List<Customer> getAllCustomerDetails()throws CustomerException {
		
		List<Customer> customers= customerRepository.findAll();
		
		if(customers.isEmpty())
			throw new CustomerException("No Customer find");
		
		return customers;
		
	}

	@Override
	public Customer getMyDetails() throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			return opt.get();
		}
	}

	@Override
	public Cart addToCart(Integer locationId, Integer restaurantId, Integer cuisineId) throws CustomerException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found please login and try again");
		}
		else {
			Customer customer = opt.get();
			
		}
	}

	




}
