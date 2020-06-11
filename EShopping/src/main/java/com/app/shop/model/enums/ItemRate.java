package com.app.shop.model.enums;

public enum ItemRate {

	BAD(0), GOOD(1), NORMAL(2), VERY_GOOD(3),EXCELLENT(4);

	private int rate;

	ItemRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}

}
