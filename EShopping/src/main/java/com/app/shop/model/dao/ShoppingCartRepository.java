package com.app.shop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shop.model.pojo.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	List<ShoppingCart> findByCartUser(long userId);
	
	@Query("FROM ShoppingCart WHERE STATUS IN ('ADD', 'PAYED_WAIT') AND USER_ID = ?1")
	List<ShoppingCart> findPendingCart(long userId);
	
	@Query(value = "SELECT COUNT(TOTAL_PRICE) FROM CartItems WHERE CART_ID = ?1")
	double getCartTotalPrice(long cartId);

}
