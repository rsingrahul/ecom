package com.tariq.ecommercedemo.model;

import java.io.Serializable;

import com.tariq.backend.dto.Cart;

public class UserModel implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	private String fullName;
	
	private String role;
	
	private Cart cart;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

}
