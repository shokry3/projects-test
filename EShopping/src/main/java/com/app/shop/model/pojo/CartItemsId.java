package com.app.shop.model.pojo;

import java.io.Serializable;

public class CartItemsId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public CartItemsId(long shopCart, long cartItem) {
		this.shopCart = shopCart;
		this.cartItem = cartItem;
	}
	
	private long shopCart;
	private long cartItem;
	
	public long getShopCart() {
		return shopCart;
	}
	public void setShopCart(long shopCart) {
		this.shopCart = shopCart;
	}
	public long getCartItem() {
		return cartItem;
	}
	public void setCartItem(long cartItem) {
		this.cartItem = cartItem;
	}

}
