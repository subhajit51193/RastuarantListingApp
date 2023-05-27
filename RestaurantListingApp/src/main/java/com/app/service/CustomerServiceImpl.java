package com.app.service;

import java.lang.module.ResolutionException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.app.exception.CuisinException;
import com.app.exception.CustomerException;
import com.app.exception.LocationException;
import com.app.exception.RestaurantException;
import com.app.model.Authority;
import com.app.model.Cart;
import com.app.model.Cuisin;
import com.app.model.Customer;
import com.app.model.Location;
import com.app.model.Restaurant;
import com.app.repository.CartRepository;
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
	
	@Autowired
	private CartRepository cartRepository;
	
	
	
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
	public Cart addToCart(Integer cuisineId, Long quantity)
			throws CustomerException, RestaurantException, LocationException, CuisinException {
		
		Optional<Customer> opt = customerRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		System.out.println(opt.get());
		if (opt.isEmpty()) {
			throw new CustomerException("Not found");
		}
		else {
			Customer customer =  opt.get();
			Optional<Cuisin> optc = cuisinRepository.findById(cuisineId);
			if (optc.isEmpty()) {
				throw new CuisinException("Not found");
			}
			else {
				Cuisin cuisin = optc.get();
				Restaurant restaurant = cuisinRepository.getRestaurantFromCuisine(cuisineId);
				Cart cart = new Cart();
				cart.setCustomer(customer);
				cart.setQuantity(quantity);
				cart.setCuisin(cuisin);
				cart.setRestaurant(restaurant);
				return cartRepository.save(cart);//not working properly giving error simple type, class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor...
			}
		}
	}

	




}
