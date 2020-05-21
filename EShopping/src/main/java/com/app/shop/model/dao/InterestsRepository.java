package com.app.shop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shop.model.pojo.UserInterests;

public interface InterestsRepository extends JpaRepository<UserInterests, Long> {
	
	List<UserInterests> findByInterstedUser(long userId);

}
