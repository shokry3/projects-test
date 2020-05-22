package com.app.mvc.model.dao;

import com.app.mvc.model.pojo.Login;
import com.app.mvc.model.pojo.User;

public interface UserDao {
	
	int register(User user);
	User validateUser(Login login);

}
