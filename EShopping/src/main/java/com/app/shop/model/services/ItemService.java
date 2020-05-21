package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.ItemRepository;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.services.srinterface.IItemService;

@Service
public class ItemService implements IItemService {
	

	@Autowired
	ItemRepository itemRepo;

	@Override
	public Optional<Item> getItemById(long id) {
		System.out.println("begin get th e Item");
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
	public List<Item> getAllItems() {
		return itemRepo.findAll();
	}

	@Override
	public Item updateItem(Item item, Item updatedItem) {
		item.setItemName(item.getItemName());
		item.setDescription(item.getDescription());
		item.setQuantity(item.getQuantity());
		item.setPrice(item.getPrice());
		item.setRate(item.getRate());
		item.setStatus(item.getStatus());
		item.setType(item.getType());
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
