package com.tariq.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.tariq.backend.dao.CategoryDao;
import com.tariq.backend.dto.Category;

public class CategoryTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static CategoryDao categoryDao;
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.tariq.backend");
		context.refresh();
		categoryDao=context.getBean("categoryDao",CategoryDao.class);
	}
	
//	@Test
//	public void testAddCategory(){
//		category = new Category();
//		category.setName("Nokia");
//		category.setDescription("Nokia description");
//		category.setImageURL("IMAGE_NOKIA");
//		category.setActive(true);
//		assertEquals("Something wrong to add!!!!!",true,categoryDao.add(category));
//	}
	
//	@Test
//	public void testGetCategory(){
//		category = categoryDao.get(1);
//		assertEquals("some thing wrong to get!!!!!","mobile",category.getName());
//	}
	
//	@Test
//	public void testUpdateCategory(){
//		category = categoryDao.get(1);
//		System.out.println(category.getName());
//		category.setName("ABC");
//		assertEquals("something wrong to update!!!!!",true,categoryDao.update(category));
//	}
	
//	@Test
//	public void testDeleteCategory(){
//		category = categoryDao.get(1);
//		assertEquals("something  wrong to delete !!!!!",true,categoryDao.delete(category));
//	}	
	
//	@Test
//	public void testFetchCategory(){
//		assertEquals("something wrong to fetch the list!!!!!",2,categoryDao.list().size());
//	}	
	
	@Test
	public void testCRUDCategory() {
		
		// add operation
		
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is some description for television!");
		category.setImageURL("CAT_2.png");
		
		assertEquals("Something wrong to added a category inside the table!",true,categoryDao.add(category));

		
		// fetching and updating the category
		category = categoryDao.get(6);
		
		category.setName("TV-demo");
		
		assertEquals("Something wrong to updated a single category in the table!",true,categoryDao.update(category));
		
		
		// delete the category
		assertEquals("Something wrong to deleted a single category in the table!",true,categoryDao.delete(category));
		
		
		//fetching the list
		assertEquals("Something wrong to fetched the list of categories from the table!",4,categoryDao.list().size());
		
	}

}
