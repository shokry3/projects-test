package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.ItemImages;

public interface IItemImagesService {
	
	Optional<ItemImages> getImageById(long id);
	
	List<ItemImages> getItemImages(long itemId);

	ItemImages updateImage(ItemImages image, ItemImages updatedimage);

	ItemImages addImage(ItemImages image);

	void deleteImage(ItemImages image);

	ItemImages saveImage(ItemImages image);
}
