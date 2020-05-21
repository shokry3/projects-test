package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.UserInterests;

public interface IUserInterestsService {
	
	public Optional<UserInterests> getItemById(long id);
	
	List<UserInterests> getUserInterests(long userId);

	UserInterests addUserInterests(long itemId, long userId);

	void deleteUserInterests(UserInterests interests);

	UserInterests saveUserInterests(UserInterests interests);

}
