package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.CartItems;
import com.app.shop.model.pojo.CartItemsId;

public interface ICartItemsService {
	
	Optional<CartItems> getItemById(long cartId, long itemId);
	
	List<CartItems> getAllItems(long cartId);

	CartItems updateItem(CartItems item, CartItems updatedItem);

	CartItems addItem(CartItems item);

	void deleteItem(CartItems item);

	CartItems saveItem(CartItems item);

}
