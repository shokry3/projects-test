package com.app.shop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shop.model.pojo.CartItems;
import com.app.shop.model.pojo.CartItemsId;

public interface CartItemsRepository extends JpaRepository<CartItems, CartItemsId> {
	
	List<CartItems> findByShopCart(long cartId);
	
}
