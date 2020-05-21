package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.ItemImagesRepository;
import com.app.shop.model.pojo.ItemImages;
import com.app.shop.model.services.srinterface.IItemImagesService;

@Service
public class ItemImagesService implements IItemImagesService{
	
	@Autowired
	ItemImagesRepository imageRepo;

	@Override
	public Optional<ItemImages> getImageById(long id) {
		try {
			return imageRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<ItemImages> getItemImages(long itemId) {
		return imageRepo.findByImageItem(itemId);
	}

	

	@Override
	public ItemImages updateImage(ItemImages image, ItemImages updatedImage) {
		image.setDescription(updatedImage.getDescription());
		image.setImage(updatedImage.getImage());
		return this.saveImage(image);
	}

	@Override
	public ItemImages addImage(ItemImages image) {
		return this.saveImage(image);
	}

	@Override
	public void deleteImage(ItemImages image) {
		// Optional<Item> Item = storeRepo.findById(id);
		if (image != null) {
			imageRepo.delete(image);
		}
	}

	@Override
	public ItemImages saveImage(ItemImages image) {
		try {
			image = imageRepo.save(image);
			return image;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}



}
