package com.app.mvc.model.service;

import com.app.mvc.model.pojo.Login;
import com.app.mvc.model.pojo.User;

public interface UserService {
	
	int register(User user);
	User validateUser(Login login);

}
