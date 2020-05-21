package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.CartItemsRepository;
import com.app.shop.model.pojo.CartItems;
import com.app.shop.model.pojo.CartItemsId;
import com.app.shop.model.services.srinterface.ICartItemsService;

@Service
public class CartItemsService implements ICartItemsService {
	
	@Autowired
	CartItemsRepository cartItemsRepo;

	@Override
	public Optional<CartItems> getItemById(long cartId, long itemId) {
		CartItemsId cartItemId = new CartItemsId(cartId, itemId);
		try {
			return cartItemsRepo.findById(cartItemId);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<CartItems> getAllItems(long cartId) {
		try {
			return cartItemsRepo.findByShopCart(cartId);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}


	@Override
	public CartItems updateItem(CartItems item, CartItems updatedItem) {
		item.setCount(updatedItem.getCount());
		double price = updatedItem.getCartItem().getPrice();
		double total = updatedItem.getCount() * price;
		item.setTotalPrice(total);
		return this.saveItem(item);
	}

	@Override
	public CartItems addItem(CartItems item) {
		double price = item.getCartItem().getPrice();
		double total = item.getCount() * price;
		item.setTotalPrice(total);
		return this.saveItem(item);
	}

	@Override
	public void deleteItem(CartItems items) {
		if (items != null) {
			cartItemsRepo.delete(items);
		}
	}

	@Override
	public CartItems saveItem(CartItems item) {
		try {
			item = cartItemsRepo.save(item);
			return item;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}


}
