package com.tariq.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tariq.backend.dao.ProductDao;
import com.tariq.backend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	
	
	private static ProductDao productDao;
	
	
	private Product product;
	
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.tariq.backend");
		context.refresh();
		productDao = (ProductDao)context.getBean("productDao");
	}
	
	@Test
	public void testCRUDProduct() {
		
		// create operation
		product = new Product();
				
		product.setName("Oppo Selfie S53");
		product.setBrand("Oppo");
		product.setDescription("This is some description for oppo mobile phones!");
		product.setUnitPrice(25000);
		product.setActive(true);
		product.setCategoryId(3);
		product.setSupplierId(3);
		
		assertEquals("Something went wrong while inserting a new product!",
				true,productDao.add(product));		
		
		
		// reading and updating the category
		product = productDao.get(2);
		product.setName("Samsung Galaxy S7");
		assertEquals("Something went wrong while updating the existing record!",
				true,productDao.update(product));		
				
		assertEquals("Something went wrong while deleting the existing record!",
				true,productDao.delete(product));		
		
		// list
		assertEquals("Something went wrong while fetching the list of products!",
				6,productDao.list().size());		
				
	}
			
	
	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!",
				5,productDao.listActiveProducts().size());				
	} 
	
	
	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDao.listActiveProductsByCategory(3).size());
		assertEquals("Something went wrong while fetching the list of products!",
				2,productDao.listActiveProductsByCategory(1).size());
	} 
	
	@Test
	public void testGetLatestActiveProduct() {
		assertEquals("Something went wrong while fetching the list of products!",
				3,productDao.getLatestActiveProducts(3).size());
		
	} 
	
	
	
		
}
