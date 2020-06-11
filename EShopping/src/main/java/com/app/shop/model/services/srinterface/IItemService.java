package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.enums.SType;
import com.app.shop.model.pojo.Item;

public interface IItemService {
	Optional<Item> getItemById(long id);

	Optional<Item> getByItemname(String Itemname);
	
	List<Item> getItemsByType(String type, long storeId);
	
	//List<Item> getTopRatingItems(String type, long storeId);  implement service to get top rating items.
	
	List<Item> getItemsByStore(long storeId);
	
	List<Item> getDealerItems(long dealerId);
	
	List<Item> getDealerItemsByType(String type, long dealerId);

	List<Item> getAllItems();

	Item updateItem(Item item, Item updatedItem);

	Item addItem(Item item);

	void deleteItem(Item item);

	Item saveItem(Item item);
}
