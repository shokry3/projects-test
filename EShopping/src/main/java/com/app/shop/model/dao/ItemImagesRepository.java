package com.app.shop.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.shop.model.pojo.ItemImages;

public interface ItemImagesRepository extends JpaRepository<ItemImages, Long> {
	
	List<ItemImages> findByImageItem(long itemId);

}
