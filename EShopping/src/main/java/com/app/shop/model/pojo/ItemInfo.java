package com.app.shop.model.pojo;

import com.app.shop.model.enums.SType;

public class ItemInfo {
	
	private SType type;
	private Long storeId;
	private Long dealerId;
	
	public SType getType() {
		return type;
	}
	public void setType(SType type) {
		this.type = type;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	public Long getDealerId() {
		return dealerId;
	}
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

}
