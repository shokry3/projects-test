package com.app.shop.model.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.UserRepository;
import com.app.shop.model.pojo.LoginRequest;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.srinterface.IAuthService;
import com.app.shop.security.jwt.JwtProvider;

@Service
public class AuthService implements IAuthService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserSevice userservice;

	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User signup(User user) {
		try {
			String cryptPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(cryptPassword);
			return userRepo.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public AuthenticationResponse login(LoginRequest loginRequest) {
		try {
			Authentication authenticate = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			String authenticationToken = jwtProvider.generateToken(authenticate);
			User loggedUser = userservice.getByUsername(loginRequest.getUsername()).get();
			AuthenticationResponse authResponse = new AuthenticationResponse(authenticationToken, loggedUser.getId(),
					loggedUser.getUsername(), loggedUser.getEmail(), loggedUser.getRoles());
			return authResponse;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}
	
	@Override
	public Optional<User> getByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
	@Override
	public Optional<User> getByMobile(String mobile) {
		return userRepo.findByMobile(mobile);
	}
	
	
	@Override
	public Optional<User> getByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser() {
		org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}

}
