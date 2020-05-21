package com.app.shop.model.services.srinterface;

import java.util.List;
import java.util.Optional;

import com.app.shop.model.pojo.StoreConfig;

public interface IStoreConfigService {
	
	Optional<StoreConfig> getStoreConfigById(int id);

	List<StoreConfig> getAllStoreConfigs();

	StoreConfig updateStoreConfig(StoreConfig storeConfig, StoreConfig updatedStoreConfig);

	StoreConfig addStoreConfig(StoreConfig storeConfig);

	void deleteStoreConfig(StoreConfig storeConfig);

	StoreConfig saveStoreConfig(StoreConfig storeConfig);

}
