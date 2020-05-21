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
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.pojo.Store;
import com.app.shop.model.services.srinterface.IStoreService;

@RestController
@RequestMapping("/store/api/s/")
public class StoreController {
	
	@Autowired
	IStoreService storeService;

	@GetMapping("/stores")
	public ResponseEntity<List<Store>> getAllStores() {
		return new ResponseEntity<>(storeService.getAllStores(), HttpStatus.OK);
	}

	@GetMapping("/stores/{id}")
	public ResponseEntity<Store> getStore(@PathVariable("id") String id) throws ResourceNotFoundException {
		Store store = new Store();
		try {
			Long num = Long.parseLong(id);
			store = storeService.getStoreById(num)
					.orElseThrow(() -> new ResourceNotFoundException("Store not found for this id :: " + id));
		} catch (NumberFormatException e) {
			store = storeService.getByStorename("" + id)
					.orElseThrow(() -> new ResourceNotFoundException("Store not found for this id :: " + id));
		}
		return ResponseEntity.ok().body(store);
	}

	@PostMapping(path = "/stores", consumes = { "application/json" })
	public ResponseEntity<Store> addStore(@Valid @RequestBody Store store) {
		Store addedStore = storeService.addStore(store);
		return ResponseEntity.ok().body(addedStore);
	}

	@PutMapping("/stores/{id}")
	public ResponseEntity<Store> updateStore(@PathVariable("id") long id, @Valid @RequestBody Store storeDetails)
			throws ResourceNotFoundException {
		Store store = storeService.getStoreById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Store not found for this id :: " + id));
		final Store updatedStore = storeService.updateStore(store, storeDetails);
		return ResponseEntity.ok(updatedStore);
	}

	@DeleteMapping("/stores/{id}")
	public Map<String, Boolean> deletStore(@PathVariable("id") long id) throws ResourceNotFoundException {
		Store store = storeService.getStoreById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Store not found for this id :: " + id));

		storeService.deleteStore(store);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}



}
