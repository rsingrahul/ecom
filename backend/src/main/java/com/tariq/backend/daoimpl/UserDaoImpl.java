package com.tariq.backend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tariq.backend.dao.UserDao;
import com.tariq.backend.dto.Address;
import com.tariq.backend.dto.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	private Address billAdd;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		try {
			return sessionFactory.getCurrentSession().createQuery(selectQuery, User.class).setParameter("email", email)
					.getSingleResult();
		} catch (Exception ex) {
			return null;
		}
	}

	@Override
	public User get(int id) {
		try {
			return sessionFactory.getCurrentSession().get(User.class, id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public boolean add(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		try {
			return sessionFactory.getCurrentSession().get(Address.class, addressId);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public boolean updateAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().update(address);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {
//		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :isBilling";
//		try {
//			return sessionFactory.getCurrentSession().createQuery(selectQuery, Address.class)
//					.setParameter("userId", userId).setParameter("isBilling", true).getSingleResult();
//		} catch (Exception ex) {
//			return null;
//		}
		
		
		return billAdd;
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		Session session = sessionFactory.getCurrentSession();
		List<Address> list = null;
		
		try{
			User user = session.createQuery("from User where id =:userId", User.class).setParameter("userId", userId).getSingleResult();
			List<Address> getList = user.getAddressz();
			list = new ArrayList<>();
			logger.info("SIZE BEFORE::::"+getList.size());
			for(Address ad:getList){
				logger.info("ADDRES::::"+ad);
				if(ad.isShipping()){
					list.add(ad);
				}
				billAdd = ad;
			}
			logger.info("SIZE AFTER::::"+list.size());
			for(Address add : list){
				logger.info("SHIPPING ADDRES::::"+add);
			}
// use this link https://www.boraji.com/hibernate-5-native-sql-query-example
//			select u.first_name, u.email, a.address_line_one, a.city, a.postal_code, a.user_id from user_detail u inner join address a on u.id=a.user_id where u.id = 5 and a.is_shipping=true; 
//			String str = "select u.first_name, u.last_name, u.role, u.email, u.contact_number, a.address_line_one,"
//					+ " a.address_line_two, a.city, a.state, a.country, a.postal_code from user_detail u inner join"
//					+ " address a on u.id=a.user_id where u.id =:userId and a.is_shipping=:isShipping";
//			List<Object[]> li = session.createNativeQuery(str).setParameter("userId", userId).setParameter("isShipping", true).getResultList();
//			logger.info("LIST::::"+list);
//			for (Object[] obj : li) {
//				for(int i=0; i<obj.length; i++){
//					System.out.println(obj[i]);
//				}
//			}
		}catch(Exception ee){
			System.out.println("errorr in fetching user details:: "+ee);
			list = null;
		}
		
//		try{
//			logger.info("USER_ID::::"+userId);
//			String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
//			Query<Address> query = session.createQuery(selectQuery, Address.class).setParameter("userId", userId)
//					.setParameter("isShipping", true);
//			logger.info("QUERY FOR ADDRESSSSSSSSSSS::::"+query);
//			list = query.getResultList();
//			logger.info("LIST OF ADDRRESSSSSSSSSSSSS::::"+list);
//		}catch(Exception e){
//			logger.error("error in fetching address:::"+e.getMessage());
//			return list = null;
//		}
		return list;
	}

}
