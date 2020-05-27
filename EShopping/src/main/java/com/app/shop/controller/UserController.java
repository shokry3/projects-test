package com.app.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.pojo.ShoppingCart;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.srinterface.IShopCartService;
import com.app.shop.model.services.srinterface.IUserService;

@RestController
@RequestMapping("/store/api/u/")
public class UserController {

	@Autowired
	IUserService userService;
	
	@Autowired
	IShopCartService userCartService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/users/{id}")
	public ResponseEntity<User> getuser(@PathVariable("id") String id) throws ResourceNotFoundException {
		User user = new User();
		try {
			Long num = Long.parseLong(id);
			user = userService.getUserById(num)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		} catch (NumberFormatException e) {
			user = userService.getByUsername("" + id)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		}
		return ResponseEntity.ok().body(user);
	}

	@PostMapping(path = "/users", consumes = { "application/json" })
	public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
		String cryptPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(cryptPassword);
		User addedUser = userService.addUser(user);
		return ResponseEntity.ok().body(addedUser);
	}

	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @Valid @RequestBody User userDetails)
			throws ResourceNotFoundException {
		User user = userService.getUserById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));
		String cryptPassword = passwordEncoder.encode(userDetails.getPassword());
		userDetails.setPassword(cryptPassword);
		final User updatedUser = userService.updateUser(user, userDetails);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/users/{id}")
	public Map<String, Boolean> deletUser(@PathVariable("id") long id) throws ResourceNotFoundException {
		User user = userService.getUserById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + id));

		userService.deleteUser(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@ResponseBody
	public ResponseEntity<Object> MessageNotReadableException(HttpMessageNotReadableException ex,
			HttpServletResponse response) {
		ex.printStackTrace();
		return new ResponseEntity<Object>("Bad Request Please Check Your Inputs", HttpStatus.BAD_REQUEST);
	}
	
	//handle front-end content ...... this need to be changed.
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}
	
	@GetMapping("/mod")
	@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
	
	
	//get shopping cart for user or create new.
	//1- get all current user requests
	@GetMapping("/allCarts/{id}")
	public ResponseEntity<List<ShoppingCart>> getAllUserCarts(@PathVariable("id") long userId) {
		return new ResponseEntity<>(userCartService.getAllUserCarts(userId), HttpStatus.OK);
	}
	
	//2- get curren shopping cart for the current user session
	@GetMapping("/carts/{id}")
	public ResponseEntity<ShoppingCart> getUserCart(@PathVariable("id") long userId)  throws ResourceNotFoundException {
		ShoppingCart cart = userCartService.getUserCart(userId)
				.orElseThrow(() -> new ResourceNotFoundException("no cart found for current user with id :: " + userId));
		return ResponseEntity.ok().body(cart);
	}
	
	
	//get logged user by username .........
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
