package com.app.shop.model.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ForeignKey;

import com.app.shop.model.enums.CartStatus;
import com.app.shop.model.enums.PayTypes;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SHOPPING_CART") //, uniqueConstraints = { @UniqueConstraint(columnNames = { "item_id", "user_id" }) }
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartid_Sequence")
	private long id;

	@JsonIgnore
	@OneToMany(mappedBy = "shopCart", cascade = CascadeType.ALL)
	Set<CartItems> items = new HashSet();

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "user_id", referencedColumnName = "ID", nullable = false)
	@ForeignKey(name = "Fk_usercart_user")
	private User cartUser;

	@NotBlank
	@Column(nullable = false)
	private double totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private PayTypes payType;

	private boolean payed;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private CartStatus status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<CartItems> getItems() {
		return items;
	}

	public void setItems(Set<CartItems> items) {
		this.items = items;
	}

	public User getCartUser() {
		return cartUser;
	}

	public void setCartUser(User cartUser) {
		this.cartUser = cartUser;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public PayTypes getPayType() {
		return payType;
	}

	public void setPayType(PayTypes payType) {
		this.payType = payType;
	}

	public boolean isPayed() {
		return payed;
	}

	public void setPayed(boolean payed) {
		this.payed = payed;
	}

	public CartStatus getStatus() {
		return status;
	}

	public void setStatus(CartStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ShoppingCart [cartId=" + id + ", cartItems=" + items + ", cartUser=" + cartUser.getId()
				+ ", totalPrice=" + totalPrice + ", payType=" + payType + ", payed=" + payed
				+ ", status=" + status + "]";
	}

}
