package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.StoreRepository;
import com.app.shop.model.pojo.Store;
import com.app.shop.model.services.srinterface.IStoreService;

@Service
public class StoreService implements IStoreService {

	@Autowired
	StoreRepository storeRepo;

	@Override
	public Optional<Store> getStoreById(long id) {
		System.out.println("begin get th e Store");
		try {
			return storeRepo.findById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	@Override
	public Optional<Store> getByStorename(String storeName) {
		return storeRepo.findByName(storeName);
	}

	@Override
	public List<Store> getAllStores() {
		return storeRepo.findAll();
	}

	@Override
	public Store updateStore(Store store, Store updatedStore) {
		store.setName(updatedStore.getName());
		store.setDescription(updatedStore.getDescription());
		store.setEmail(updatedStore.getEmail());
		store.setHeadLocation(updatedStore.getHeadLocation());
		store.setType(updatedStore.getType());
		store.setMobile(updatedStore.getMobile());
		return this.saveStore(store);
	}

	@Override
	public Store addStore(Store Store) {
		return this.saveStore(Store);
	}

	@Override
	public void deleteStore(Store Store) {
		// Optional<Store> Store = storeRepo.findById(id);
		if (Store != null) {
			storeRepo.delete(Store);
		}
	}

	@Override
	public Store saveStore(Store store) {
		try {
			store = storeRepo.save(store);
			return store;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
