package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.InterestsRepository;
import com.app.shop.model.dao.ItemRepository;
import com.app.shop.model.dao.UserRepository;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.User;
import com.app.shop.model.pojo.UserInterests;
import com.app.shop.model.services.srinterface.IUserInterestsService;

@Service
public class UserInterestsService implements IUserInterestsService {
	
	@Autowired
	InterestsRepository interestsRepo;
	
	@Autowired
	ItemRepository intItemRepo;
	
	@Autowired
	UserRepository intUserRepo;

	@Override
	public Optional<UserInterests> getItemById(long id) {
		try {
			return interestsRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
	@Override
	public List<UserInterests> getUserInterests(long userId) {
		try {
			return interestsRepo.findByInterstedUser(userId);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public UserInterests addUserInterests(long itemId, long userId) {
		UserInterests interest = new UserInterests();
		Item item = intItemRepo.findById(itemId).get();
		User user = intUserRepo.findById(userId).get();
		if(item != null) {
			
			interest.setInterstedItem(item);
			interest.setInterestType(item.getType());
			if(user != null) {
				interest.setInterstedUser(user);
			}
			interest = this.saveUserInterests(interest);
			
		}
		return interest;
		
	}

	@Override
	public void deleteUserInterests(UserInterests item) {
		if (item != null) {
			interestsRepo.delete(item);
		}
		
	}

	@Override
	public UserInterests saveUserInterests(UserInterests item) {
		try {
			item = interestsRepo.save(item);
			return item;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
