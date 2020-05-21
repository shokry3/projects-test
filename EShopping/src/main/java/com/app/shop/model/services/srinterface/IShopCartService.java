package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.ShoppingCart;

public interface IShopCartService {
	
	public Optional<ShoppingCart> getCartById(long id);
	
	List<ShoppingCart> getAllUserCarts(long userId);
	
	Optional<ShoppingCart> getUserCart(Long userId);

	ShoppingCart updateCart(ShoppingCart cart, ShoppingCart updatedcart);

	ShoppingCart addCart(ShoppingCart cart, long userId);

	void deleteCart(ShoppingCart cart);

	ShoppingCart saveCart(ShoppingCart cart);

}
