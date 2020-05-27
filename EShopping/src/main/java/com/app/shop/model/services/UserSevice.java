package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.UserRepository;
import com.app.shop.model.pojo.User;
import com.app.shop.model.services.srinterface.IUserService;

@Service
public class UserSevice implements IUserService {

	@Autowired
	UserRepository userRepo;

	@Override
	public Optional<User> getUserById(long id) {
		System.out.println("begin get th e user");
		try {
			return userRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public Optional<User> getByUsername(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	 
	@Override
	public User updateUser(User user, User updatedUser) {
		user.setFullname(updatedUser.getFullname());
		user.setEmail(updatedUser.getEmail());
		user.setAddress(updatedUser.getAddress());
		user.setMobile(updatedUser.getMobile());
		user.setPassword(updatedUser.getPassword());
		user.setPhoto(updatedUser.getPhoto());
		user.setPostalcode(updatedUser.getPostalcode());
		return this.saveUser(user);
	}

	@Override
	public User addUser(User user) {
		return this.saveUser(user);
	}

	@Override
	public void deleteUser(User user) {
		// Optional<User> user = userRepo.findById(id);
		if (user != null) {
			userRepo.delete(user);
		}
	}

	@Override
	public User saveUser(User user) {
		try {
			user = userRepo.save(user);
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
