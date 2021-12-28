package com.tariq.ecommercedemo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.tariq.backend.dao.UserDao;
import com.tariq.backend.dto.User;
import com.tariq.ecommercedemo.model.UserModel;

@ControllerAdvice
public class GlobalController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserDao userDao;
	
	private UserModel userModel = null;
	private User user = null;
	
	@ModelAttribute("userModel")
	public UserModel getUserModel() {		
		if(session.getAttribute("userModel")==null) {
			// get the authentication object
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			
			if(!authentication.getPrincipal().equals("anonymousUser")){
				// get the user from the database
				user = userDao.getByEmail(authentication.getName());
				
				if(user!=null) {
					userModel = new UserModel();
					userModel.setId(user.getId());
					userModel.setFullName(user.getFirstName() + " " + user.getLastName());
					userModel.setRole(user.getRole());
					
					if(user.getRole().equals("USER")) {
						userModel.setCart(user.getCart());					
					}				
	
					session.setAttribute("userModel", userModel);
					return userModel;
				}			
			}
		}
		
		return (UserModel) session.getAttribute("userModel");		
	}

}
