package com.app.shop.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.app.shop.model.pojo.Role;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;

	public AuthenticationResponse(String accessToken, Long id, String username, String email, Set<Role> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		for(Role role :roles) {
			this.roles = new ArrayList<>();
			this.roles.add(role.getName().name());
		}
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<String> getRoles() {
		return roles;
	}
}
