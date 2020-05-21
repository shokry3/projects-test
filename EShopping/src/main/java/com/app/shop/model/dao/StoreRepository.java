package com.app.shop.model.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shop.model.pojo.Store;

public interface StoreRepository extends JpaRepository<Store, Long>{
	
	Optional<Store> findByName(String name);
}
