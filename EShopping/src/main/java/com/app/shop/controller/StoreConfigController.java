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
import com.app.shop.model.pojo.StoreConfig;
import com.app.shop.model.services.srinterface.IStoreConfigService;

@RestController
@RequestMapping("/store/api/o/")
public class StoreConfigController {
	
	@Autowired
	IStoreConfigService storeConfigService;

	@GetMapping("/configs/{id}")
	public ResponseEntity<StoreConfig> getStoreConfig(@PathVariable("id") int id) throws ResourceNotFoundException {
		StoreConfig storeConfig = new StoreConfig();
		storeConfig = storeConfigService.getStoreConfigById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StoreConfig not found for this id :: " + id));
		return ResponseEntity.ok().body(storeConfig);
	}

	@PostMapping(path = "/configs", consumes = { "application/json" })
	public ResponseEntity<StoreConfig> addStoreConfig(@Valid @RequestBody StoreConfig StoreConfig) {
		StoreConfig addedStoreConfig = storeConfigService.addStoreConfig(StoreConfig);
		return ResponseEntity.ok().body(addedStoreConfig);
	}

	@PutMapping("/configs/{id}")
	public ResponseEntity<StoreConfig> updateStoreConfig(@PathVariable("id") int id, @Valid @RequestBody StoreConfig configDetails)
			throws ResourceNotFoundException {
		StoreConfig storeConfig = storeConfigService.getStoreConfigById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StoreConfig not found for this id :: " + id));
		final StoreConfig updatedStoreConfig = storeConfigService.updateStoreConfig(storeConfig, configDetails);
		return ResponseEntity.ok(updatedStoreConfig);
	}

	@DeleteMapping("/configs/{id}")
	public Map<String, Boolean> deletStoreConfig(@PathVariable("id") int id) throws ResourceNotFoundException {
		StoreConfig storeConfig = storeConfigService.getStoreConfigById(id)
				.orElseThrow(() -> new ResourceNotFoundException("StoreConfig not found for this id :: " + id));
		storeConfigService.deleteStoreConfig(storeConfig);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


}
