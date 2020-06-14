package com.app.shop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.shop.model.pojo.ItemImages;

public interface ItemImagesRepository extends JpaRepository<ItemImages, Long> {
	
	@Query(value = "FROM ItemImages WHERE ITEM_ID =?1 ORDER BY ID ASC")
	List<ItemImages> findByImageItem(long itemId);

}
