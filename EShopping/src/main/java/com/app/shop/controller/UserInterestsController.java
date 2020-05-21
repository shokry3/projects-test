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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.UserInterests;
import com.app.shop.model.services.srinterface.IUserInterestsService;

@RestController
@RequestMapping("/store/api/i/")
public class UserInterestsController {
	
	@Autowired
	IUserInterestsService interestsService;
	
	@GetMapping("/interests/{id}")
	public ResponseEntity<List<UserInterests>> getUserInterests(@PathVariable("id") long userId) {
		return new ResponseEntity<>(interestsService.getUserInterests(userId), HttpStatus.OK);
	}
	
	@PostMapping(path = "/interests/{tId}/{uId}", consumes = { "application/json" })
	public ResponseEntity<UserInterests> addItem(@PathVariable("tId") long itemId, @PathVariable("uId") long userId) {
		UserInterests addedItem = interestsService.addUserInterests(itemId, userId);
		return ResponseEntity.ok().body(addedItem);
	}
	
	@DeleteMapping("/interests/{id}")
	public Map<String, Boolean> deletItem(@PathVariable("id") long id) throws ResourceNotFoundException {
		UserInterests item = interestsService.getItemById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + id));

		interestsService.deleteUserInterests(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
