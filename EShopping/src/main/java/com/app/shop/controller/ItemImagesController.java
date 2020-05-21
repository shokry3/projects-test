package com.app.shop.controller;

import java.util.ArrayList;
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
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.ItemImages;
import com.app.shop.model.services.srinterface.IItemImagesService;

@RestController
@RequestMapping("/store/api/m/")
public class ItemImagesController {

	@Autowired
	IItemImagesService imageService;

	@GetMapping("/images/{id}")
	public ResponseEntity<ItemImages> getImage(@PathVariable("id") long id) throws ResourceNotFoundException {
		ItemImages image = new ItemImages();
		image = imageService.getImageById(id)
				.orElseThrow(() -> new ResourceNotFoundException("image not found for this id :: " + id));

		return ResponseEntity.ok().body(image);
	}

	@GetMapping("/allImages/{id}")
	public ResponseEntity<List<ItemImages>> getItemImages(@PathVariable("id") long id) {
		List<ItemImages> images = new ArrayList<>();
		images = imageService.getItemImages(id);
		return new ResponseEntity<>(images, HttpStatus.OK);
	}

	@PostMapping(path = "/images", consumes = { "application/json" })
	public ResponseEntity<ItemImages> addImage(@Valid @RequestBody ItemImages image) {
		ItemImages addedImage = imageService.addImage(image);
		return ResponseEntity.ok().body(addedImage);
	}

	@PutMapping("/images/{id}")
	public ResponseEntity<ItemImages> updateItem(@PathVariable("id") long id, @Valid @RequestBody ItemImages imageDetails)
			throws ResourceNotFoundException {
		ItemImages image = imageService.getImageById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Image not found for this id :: " + id));
		final ItemImages updatedImage = imageService.updateImage(image, imageDetails);
		return ResponseEntity.ok(updatedImage);
	}
	
	@DeleteMapping("/images/{id}")
	public Map<String, Boolean> deletItem(@PathVariable("id") long id) throws ResourceNotFoundException {
		ItemImages image = imageService.getImageById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Image not found for this id :: " + id));

		imageService.deleteImage(image);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
