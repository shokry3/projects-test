package com.app.shop.model.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.shop.model.enums.SType;
import com.app.shop.model.pojo.Item;


public interface ItemRepository extends JpaRepository<Item, Long> {

	Optional<Item> findByItemName(String name);
	
	//Return items by optional filter parameter (store_id, dealer_id, type) or get all items in case null parameter value
	@Query(value = "SELECT t FROM Item t WHERE (:sid is null or t.itemStore.id = :sid) and "
			+ "(:did is null or t.itemDealer.id = :did) and "
			+ "(:tid is null or t.type = :tid) ORDER BY RATE DESC")
	List<Item> findByItemStore(@Param("sid") Long storeId, @Param("did") Long dealerId, @Param("tid") SType type);
	
	
	
	//methods not used yet......
//	@Query(value = "FROM Item WHERE TYPE = ?1 AND STORE_ID =?2 ORDER BY RATE DESC")
//	List<Item> findByType(String type, long storeId); 
//	
//	@Query(value = "FROM Item WHERE DEALER_ID =?1 ORDER BY RATE DESC")
//	List<Item> findByItemDealer(long dealerId);
//	
//	@Query(value = "FROM Item WHERE TYPE = ?1 AND  DEALER_ID =?1 ORDER BY RATE DESC")
//	List<Item> findDealerItemsByType(String type, long dealerId); 
	
}
