package com.app.shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.shop.exceptionhandel.ResourceNotFoundException;
import com.app.shop.model.pojo.LoginRequest;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.AuthService;
import com.app.shop.model.services.AuthenticationResponse;

@RestController
@RequestMapping("/store/api/auth/")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody User user) {
		User addedUser = authService.signup(user);
		return ResponseEntity.ok().body(addedUser);
	}

	@PostMapping("/login")
	public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	//to validate user name register ....
	@GetMapping("/username/{id}")
	public ResponseEntity<String> getUsername(@PathVariable("id") String id) throws ResourceNotFoundException {
		User user = new User();
		try {
			user = authService.getByUsername("" + id)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this username :: " + id));
		} catch (NumberFormatException e) {
		}
		return ResponseEntity.ok().body(user.getUsername());
	}
	
	//to validate user email register.....
	@GetMapping("/useremail")
	public ResponseEntity<String> getUserEmail(@RequestParam String id) throws ResourceNotFoundException {
		User user = new User();
		try {
			user = authService.getByEmail("" + id)
					.orElseThrow(() -> new ResourceNotFoundException("User not found for this email :: " + id));
		} catch (NumberFormatException e) {
		}
		return ResponseEntity.ok().body(user.getEmail());
	}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;

	}

}
