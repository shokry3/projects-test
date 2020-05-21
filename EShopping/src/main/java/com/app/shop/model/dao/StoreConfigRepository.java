package com.app.shop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shop.model.pojo.StoreConfig;

public interface StoreConfigRepository extends JpaRepository<StoreConfig, Integer> {
	
	List<StoreConfig> findByStoreConfig(long storeId);

}
