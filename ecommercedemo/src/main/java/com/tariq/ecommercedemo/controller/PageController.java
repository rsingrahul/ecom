package com.tariq.ecommercedemo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tariq.backend.dao.CategoryDao;
import com.tariq.backend.dao.ProductDao;
import com.tariq.backend.dto.Category;
import com.tariq.backend.dto.Product;
import com.tariq.ecommercedemo.exception.ProductNotFoundException;


@Controller
public class PageController {
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	
	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ProductDao productDao;
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index(@RequestParam(name="logout",required=false)String logout) {

		ModelAndView mv = new ModelAndView("page");
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		mv.addObject("title", "Home");
		
		mv.addObject("categories", categoryDao.list());
		
		if(logout!=null) {
			mv.addObject("message", "You have successfully logged out!");			
		}
		
		mv.addObject("userClickHome",true);
		
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {

		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);
		mv.addObject("categories", categoryDao.list());
		return mv;
	}
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {

		ModelAndView mv = new ModelAndView("page");
		
		Category category = null;
		category = categoryDao.get(id);
		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProducts", true);
		mv.addObject("categories", categoryDao.list());
		mv.addObject("category", category);
		return mv;
	}
	
	@RequestMapping("/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException{
		
		ModelAndView mv =  new ModelAndView("page");
		Product product = productDao.get(id);
		if(product == null) throw new ProductNotFoundException();
		product.setViews(product.getViews() + 1);
		productDao.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		
		return mv;
		
	}
	
	@RequestMapping(value="/membership")
	public ModelAndView register() {
		ModelAndView mv= new ModelAndView("page");
		
		logger.info("Page Controller membership called!");
		
		return mv;
	}
	
	@RequestMapping(value="/login")
	public ModelAndView login(@RequestParam(name="error", required = false)	String error,
			@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv= new ModelAndView("login");
		mv.addObject("title", "Login");
		if(error!=null) {
			mv.addObject("message", "Username and Password is invalid!");
		}
		if(logout!=null) {
			mv.addObject("logout", "You have logged out successfully!");
		}
		return mv;
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		// Invalidates HTTP Session, then unbinds any objects bound to it.
	    // Removes the authentication from securitycontext 		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
		
		return "redirect:/login?logout";
	}	
	
	
	@RequestMapping(value="/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");		
		mv.addObject("errorTitle", "Aha! Caught You.");		
		mv.addObject("errorDescription", "You are not authorized to view this page!");		
		mv.addObject("title", "403 Access Denied");		
		return mv;
	}	

}
