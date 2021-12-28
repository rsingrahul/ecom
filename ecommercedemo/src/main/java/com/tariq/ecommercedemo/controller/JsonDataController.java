package com.tariq.ecommercedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tariq.backend.dao.ProductDao;
import com.tariq.backend.dto.Product;

@RestController
@RequestMapping("/json/data")
public class JsonDataController {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/admin/all/products")
	public List<Product> getAllProductsList() {		
		return productDao.list();
				
	}	
	
	
	
	@GetMapping("/all/products")
	public List<Product> getAllProducts(){
		
		return productDao.listActiveProducts();
	}
	
	@GetMapping("/category/{id}/products")
	public List<Product> getProductsByCategory(@PathVariable int id){
		
		return productDao.listActiveProductsByCategory(id);
	}
	
	@RequestMapping("/mv/products")
	public List<Product> getMostViewedProducts() {		
		return productDao.getProductsByParam("views", 5);				
	}
		
	@RequestMapping("/mp/products")
	public List<Product> getMostPurchasedProducts() {		
		return productDao.getProductsByParam("purchases", 5);				
	}

}
