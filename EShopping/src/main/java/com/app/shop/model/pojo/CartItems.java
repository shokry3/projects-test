package com.app.shop.model.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CART_ITEMS")
@IdClass(CartItemsId.class)
public class CartItems implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotBlank
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "cart_id", referencedColumnName="ID", nullable = false)
	@ForeignKey(name = "Fk_shopCart_items")
	private ShoppingCart shopCart;
	
	@Id
	@NotBlank
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "item_id", referencedColumnName="ID", nullable = false)
	@ForeignKey(name = "Fk_itemcart_item")
	private Item cartItem;
	
	@NotBlank
	@Column(nullable = false)
	private int count;
	
	@NotBlank
	@Column(nullable = false)
	private double totalPrice;

	public ShoppingCart getShopCart() {
		return shopCart;
	}

	public void setShopCart(ShoppingCart shopCart) {
		this.shopCart = shopCart;
	}

	public Item getCartItem() {
		return cartItem;
	}

	public void setCartItem(Item cartItem) {
		this.cartItem = cartItem;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "CartItems [shopCart=" + shopCart + ", cartItem=" + cartItem + ", count=" + count + ", totalPrice="
				+ totalPrice + "]";
	}

}
