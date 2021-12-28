package com.tariq.backend.dao;

import java.util.List;

import com.tariq.backend.dto.Address;
import com.tariq.backend.dto.User;

public interface UserDao {
	
	User getByEmail(String email);
	
	User get(int id);

	boolean add(User user);
	
	// adding and updating a new address
	Address getAddress(int addressId);
	
	boolean addAddress(Address address);
	
	boolean updateAddress(Address address);
	
	Address getBillingAddress(int userId);
	
	List<Address> listShippingAddresses(int userId);	

}
