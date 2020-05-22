package com.app.mvc.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.app.mvc.model.dao.UserDao;
import com.app.mvc.model.pojo.Login;
import com.app.mvc.model.pojo.User;

@Component
public class UserServiceImpl implements UserService {

	 @Autowired
	  public UserDao userDao;

	  public int register(User user) {
	    return userDao.register(user);
	  }

	  public User validateUser(Login login) {
	    return userDao.validateUser(login);
	  }

}
