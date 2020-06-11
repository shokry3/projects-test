package com.app.shop.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shop.model.enums.SType;
import com.app.shop.model.pojo.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findByItemName(String name);
	
	@Query(value = "FROM Item WHERE DEALER_ID =?1 ORDER BY RATE DESC")
	List<Item> findByItemDealer(long dealerId);
	
	@Query(value = "FROM Item WHERE TYPE = ?1 AND  DEALER_ID =?1 ORDER BY RATE DESC")
	List<Item> findDealerItemsByType(String type, long dealerId); 
	
	@Query(value = "FROM Item WHERE STORE_ID =?1 ORDER BY RATE DESC")
	List<Item> findByItemStore(long storeId);
	
	@Query(value = "FROM Item WHERE TYPE = ?1 AND STORE_ID =?2 ORDER BY RATE DESC")
	List<Item> findByType(String type, long storeId); 
	
}
