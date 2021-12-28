package com.tariq.backend.dao;

import java.util.List;

import com.tariq.backend.dto.Cart;
import com.tariq.backend.dto.CartLine;
import com.tariq.backend.dto.OrderDetail;


public interface CartLineDao {
	
	public List<CartLine> list(int cartId);
	
	public CartLine get(int id);	
	
	public boolean add(CartLine cartLine);
	
	public boolean update(CartLine cartLine);
	
	public boolean remove(CartLine cartLine);
	
	public List<CartLine> listAvailable(int cartId);
	
	public CartLine getByCartAndProduct(int cartId, int productId);	
	
	boolean updateCart(Cart cart);
	
	boolean addOrderDetail(OrderDetail orderDetail);

}
