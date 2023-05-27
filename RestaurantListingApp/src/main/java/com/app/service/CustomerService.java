package com.app.service;
import java.util.List;

import org.springframework.security.core.Authentication;

import com.app.exception.CuisinException;
import com.app.exception.CustomerException;
import com.app.exception.LocationException;
import com.app.exception.RestaurantException;
import com.app.model.Cart;
import com.app.model.Customer;

public interface CustomerService {
	
	public Customer registerCustomer(Customer customer);
	
	public Customer getCustomerDetailsByEmail(String email)throws CustomerException;
	
	public List<Customer> getAllCustomerDetails()throws CustomerException;
	
	public Customer getMyDetails()throws CustomerException;
	
//	---------------------------------------------------------------
	
	public Cart addToCart(Integer cuisineId,Long quantity) throws CustomerException,RestaurantException,LocationException,CuisinException;
}
