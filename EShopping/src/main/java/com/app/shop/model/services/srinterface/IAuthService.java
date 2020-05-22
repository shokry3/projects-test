package com.app.shop.model.services.srinterface;

import com.app.shop.model.pojo.LoginRequest;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.AuthenticationResponse;

public interface IAuthService {
	
	 public User signup(User user);
	 
	 public AuthenticationResponse login(LoginRequest loginRequest);

}
