package com.app.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.pojo.CartItems;
import com.app.shop.model.pojo.CartItemsId;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.ShoppingCart;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.srinterface.ICartItemsService;
import com.app.shop.model.services.srinterface.IItemService;
import com.app.shop.model.services.srinterface.IShopCartService;
import com.app.shop.model.services.srinterface.IUserService;

@RestController
@RequestMapping("/store/api/c/")
public class ShopCartController {


	@Autowired
	IShopCartService cartService;
	
	@Autowired
	ICartItemsService cartItemService;
	
	@Autowired
	IUserService userService;
	
	// 1- End points methods for shopping cart  services
	@PutMapping("/carts/{id}")
	public ResponseEntity<ShoppingCart> updateCart( @PathVariable("id") long cartId, ShoppingCart cartDetails) throws ResourceNotFoundException {
		ShoppingCart cart = cartService.getCartById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id :: " + cartId));
		if(getLoggedinUser() != null) {
			cart.setCartUser(getLoggedinUser());
		}
		final ShoppingCart updatedItem = cartService.updateCart(cart, cartDetails);
		return ResponseEntity.ok(updatedItem);
	}
	
	@DeleteMapping("/carts/{id}")
	public Map<String, Boolean> deleteCart( @PathVariable("id") long cartId) throws ResourceNotFoundException {
		ShoppingCart cart = cartService.getCartById(cartId)
				.orElseThrow(() -> new ResourceNotFoundException("Cart not found for this id :: " + cartId));

		cartService.deleteCart(cart);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	
    // 2- End points methods for cart items services
	@GetMapping("/items")
	public ResponseEntity<List<CartItems>> getAllItems(long cartId) {
		return new ResponseEntity<>(cartItemService.getAllItems(cartId), HttpStatus.OK);
	}

	@PostMapping(path = "/items", consumes = { "application/json" })
	public ResponseEntity<CartItems> addItem(@Valid @RequestBody CartItems item) {
		CartItems addedItem = cartItemService.addItem(item);
		if(getLoggedinUser() != null) {
			addedItem.getShopCart().setCartUser(getLoggedinUser());
		}
		return ResponseEntity.ok().body(addedItem);
	}

	@PutMapping("/items/{cId}/{id}")
	public ResponseEntity<CartItems> updateItem(@PathVariable("cId") long cartId, @PathVariable("id") long itemId,
			@Valid @RequestBody CartItems itemDetails) throws ResourceNotFoundException {
		CartItems item = cartItemService.getItemById(cartId, itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found in cart for this id :: " + itemId));
		final CartItems updatedItem = cartItemService.updateItem(item, itemDetails);
		return ResponseEntity.ok(updatedItem);
	}

	@DeleteMapping("/items/{cId}/{id}")
	public Map<String, Boolean> deleteItem(@PathVariable("cId") long cartId, @PathVariable("id") long itemId) throws ResourceNotFoundException {
		CartItems item = cartItemService.getItemById(cartId, itemId)
				.orElseThrow(() -> new ResourceNotFoundException("Item not found in cart  for this id :: " + itemId));

		cartItemService.deleteItem(item);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	
	//get logged user by username to update cart with logged user
	private User getLoggedinUser(){
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			if(((UserDetails) principal).getUsername() != null) {
				return userService.getByUsername(((UserDetails) principal).getUsername()).get();
			}
		}
		
		return null;
	}

}
