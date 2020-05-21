package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.User;


public interface IUserService {

	Optional<User> getUserById(long id);
	
	Optional<User> getByUsername(String username);
	
	List<User> getAllUsers();

	User updateUser(User user, User updatedUser);

	User addUser(User user);

	void deleteUser(User user);
	
	User saveUser(User user);
	
	
}
