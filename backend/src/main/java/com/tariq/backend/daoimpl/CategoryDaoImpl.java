package com.tariq.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tariq.backend.dao.CategoryDao;
import com.tariq.backend.dto.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Category> list() {
		
		String sql = "from Category where active=:active";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		query.setParameter("active", true);
		return query.getResultList();
	}

	@Override
	public Category get(int id) {
		
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {
		try{
			sessionFactory.getCurrentSession().merge(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
