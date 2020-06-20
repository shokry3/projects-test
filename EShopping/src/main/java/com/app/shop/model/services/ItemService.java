package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.ItemRepository;
import com.app.shop.model.enums.SType;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.ItemInfo;
import com.app.shop.model.services.srinterface.IItemService;

@Service
public class ItemService implements IItemService {
	

	@Autowired
	ItemRepository itemRepo;

	@Override
	public Optional<Item> getItemById(long id) {
		try {
			return itemRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Optional<Item> getByItemname(String itemName) {
		return itemRepo.findByItemName(itemName);
	}
	
	@Override
	public List<Item> getItemsByStore(ItemInfo itemInfo) {
		return itemRepo.findByItemStore(itemInfo.getStoreId(), itemInfo.getDealerId(), itemInfo.getType());
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepo.findAll();
	}

	@Override
	public Item updateItem(Item item, Item updatedItem) {
		item.setItemName(updatedItem.getItemName());
		item.setDescription(updatedItem.getDescription());
		item.setQuantity(updatedItem.getQuantity());
		item.setPrice(updatedItem.getPrice());
		item.setRate(updatedItem.getRate());
		item.setStatus(updatedItem.getStatus());
		item.setType(updatedItem.getType());
		return this.saveItem(item);
	}

	@Override
	public Item addItem(Item item) {
		return this.saveItem(item);
	}

	@Override
	public void deleteItem(Item item) {
		// Optional<Item> item = storeRepo.findById(id);
		if (item != null) {
			itemRepo.delete(item);
		}
	}

	@Override
	public Item saveItem(Item item) {
		try {
			item = itemRepo.save(item);
			return item;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}


}
