package com.tariq.ecommercedemo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.tariq.backend.dao.UserDao;
import com.tariq.backend.dto.Address;
import com.tariq.backend.dto.Cart;
import com.tariq.backend.dto.User;
import com.tariq.ecommercedemo.model.RegisterModel;


@Component
public class RegisterHandler {
	
	@Autowired
	 private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	public RegisterModel init() {
		return new RegisterModel();
	}

	public void addUser(RegisterModel registerModel, User user) {
		registerModel.setUser(user);
	}

	public void addBilling(RegisterModel registerModel, Address billing) {
		registerModel.setBilling(billing);
	}

	public String validateUser(User user, MessageContext error) {
		String transitionValue = "success";
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match confirm password!").build());
			transitionValue = "failure";
		}
		if (userDao.getByEmail(user.getEmail()) != null) {
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email address is already taken!")
					.build());
			transitionValue = "failure";
		}
		return transitionValue;
	}

	public String saveAll(RegisterModel registerModel) {
		String transitionValue = "success";
		User user = registerModel.getUser();
		if (user.getRole().equals("USER")) {
			// create a new cart
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}

		// encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		// save the user
		userDao.add(user);
		// save the billing address
		Address billing = registerModel.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		userDao.addAddress(billing);
		return transitionValue;
	}

}
