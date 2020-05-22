package com.app.shop.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.shop.model.pojo.LoginRequest;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.AuthService;
import com.app.shop.model.services.AuthenticationResponse;

@Controller
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
	
	//for test back-end login
//	@RequestMapping("/login")
//	public String loginPage() {
//		return "login.jsp";
//
//	}

	//for test back-end login
//	@RequestMapping("/logout-success")
//	public String logoutPage() {
//		return "logout.jsp";
//
//	}

	@RequestMapping("/user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;

	}

}
