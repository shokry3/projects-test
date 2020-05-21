package com.app.shop.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shop.model.pojo.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findByItemName(String name);
	List<Item> findByItemStore(long storeId);  
	List<Item> findByItemDealer(long dealerId); 
}
