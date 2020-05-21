package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.Store;

public interface IStoreService {
	
	Optional<Store> getStoreById(long id);

	Optional<Store> getByStorename(String storename);

	List<Store> getAllStores();

	Store updateStore(Store store, Store updatedStore);

	Store addStore(Store store);

	void deleteStore(Store store);

	Store saveStore(Store store);

}
