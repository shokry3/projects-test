package com.app.shop.model.enums;

public enum ItemRate {

	EXCELLENT(5), VERY_GOOD(4), NORMAL(3), GOOD(2), BAD(1);

	private int rate;

	ItemRate(int rate) {
		this.rate = rate;
	}

	public int getRate() {
		return rate;
	}

}
