package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.StoreConfigRepository;
import com.app.shop.model.pojo.StoreConfig;
import com.app.shop.model.services.srinterface.IStoreConfigService;

@Service
public class StoreConfigService implements IStoreConfigService{
	

	@Autowired
	StoreConfigRepository storeConfigRepo;

	@Override
	public Optional<StoreConfig> getStoreConfigById(int id) {
		try {
			return storeConfigRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}


	@Override
	public List<StoreConfig> getAllStoreConfigs() {
		return storeConfigRepo.findAll();
	}

	@Override
	public StoreConfig updateStoreConfig(StoreConfig storeConfig, StoreConfig updatedStoreConfig) {
		storeConfig.setCurrency(updatedStoreConfig.getCurrency());
		storeConfig.setLocale(updatedStoreConfig.getLocale());
		storeConfig.setVat(updatedStoreConfig.getVat());
		return this.saveStoreConfig(storeConfig);
	}

	@Override
	public StoreConfig addStoreConfig(StoreConfig storeConfig) {
		return this.saveStoreConfig(storeConfig);
	}

	@Override
	public void deleteStoreConfig(StoreConfig storeConfig) {
		// Optional<StoreConfig> StoreConfig = storeRepo.findById(id);
		if (storeConfig != null) {
			storeConfigRepo.delete(storeConfig);
		}
	}

	@Override
	public StoreConfig saveStoreConfig(StoreConfig storeConfig) {
		try {
			storeConfig = storeConfigRepo.save(storeConfig);
			return storeConfig;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}


}
