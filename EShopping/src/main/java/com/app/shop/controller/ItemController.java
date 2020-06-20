package com.app.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.enums.SType;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.ItemInfo;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.srinterface.IItemService;

@RestController
@RequestMapping("/store/api/t/")
public class ItemController {

	@Autowired
	IItemService itemService;

	// End point method not used yet......
//	@GetMapping("/items")
//	public ResponseEntity<List<Item>> getAllItems() {
//		return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
//	}

	// End point methods to get store items by item info (store_id, dealer_id, type)
	// or get all items in case null parameter value.
	@PostMapping(path = "/allItems", consumes = {"application/json"})
	public ResponseEntity<List<Item>> getAllItems(@RequestBody(required = false) ItemInfo itemInfo) {
		if (itemInfo == null) {
			itemInfo = new ItemInfo();
		}
		return new ResponseEntity<>(itemService.getItemsByStore(itemInfo), HttpStatus.OK);
	}

	@GetMapping("/items/{id}")
	public ResponseEntity<Item> getItem(@PathVariable("id") String id) throws ResourceNotFoundException {
		Item item = new Item();
		try {
			Long num = Long.parseLong(id);
			item = itemService.getItemById(num)
					.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + id));
		} catch (NumberFormatException e) {
			item = itemService.getByItemname("" + id)
					.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + id));
		}
		return ResponseEntity.ok().body(item);
	}
	
	//to validate user email register.....
		@GetMapping("/itemname")
		public ResponseEntity<String> getUserEmail(@RequestParam String id) throws ResourceNotFoundException {
			Item item = new Item();
			try {
				item = itemService.getByItemname(id)
						.orElseThrow(() -> new ResourceNotFoundException("Item not found for this Name :: " + id));
			} catch (NumberFormatException e) {
			}
			return ResponseEntity.ok().body(item.getItemName());
		}

	@PostMapping(path = "/items")
	public ResponseEntity<Item> addItem(@Valid @RequestBody Item item) {
		Item addedItem = itemService.addItem(item);
//		System.out.println("here addeddddd items .... " + addedItem);
		return ResponseEntity.ok().body(addedItem);
	}

	@PutMapping("/items/{id}")
	public ResponseEntity<Item> updateItem(@PathVariable("id") long id, @Valid @RequestBody Item itemDetails)
			throws ResourceNotFoundException {
		//System.out.println("here update items .... " + itemDetails);
		Item item = itemService.getItemById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + id));
		final Item updatedItem = itemService.updateItem(item, itemDetails);
		return ResponseEntity.ok(updatedItem);
	}

	@DeleteMapping("/items/{id}")
	public Map<String, Boolean> deletItem(@PathVariable("id") long id) throws ResourceNotFoundException {
		Item item = itemService.getItemById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + id));

		itemService.deleteItem(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
