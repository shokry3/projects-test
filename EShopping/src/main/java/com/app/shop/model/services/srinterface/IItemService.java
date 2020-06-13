package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.enums.SType;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.ItemInfo;

public interface IItemService {
	Optional<Item> getItemById(long id);

	Optional<Item> getByItemname(String Itemname);
	
	//List<Item> getTopRatingItems(String type, long storeId);  implement service to get top rating items.
	
	List<Item> getItemsByStore(ItemInfo itemInfo);

	List<Item> getAllItems();

	Item updateItem(Item item, Item updatedItem);

	Item addItem(Item item);

	void deleteItem(Item item);

	Item saveItem(Item item);
}
