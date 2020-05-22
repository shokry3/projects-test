package com.app.shop.model.services;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String username;
    
    public AuthenticationResponse(String authenticationToken, String username) {
    	this.authenticationToken = authenticationToken;
    	this.username = username;
    	
    }
}
