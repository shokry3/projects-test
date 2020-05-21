package com.app.shop.model.pojo;

import java.util.Date;
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

import com.app.shop.model.enums.ItemRate;
import com.app.shop.model.enums.ItemStatus;
import com.app.shop.model.enums.SType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "items", uniqueConstraints = { @UniqueConstraint(columnNames = "itemName") })
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemid_Sequence")
	private long id;

	@NotBlank
	@Column(nullable = false)
	private String itemName;

	@NotBlank
	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private ItemStatus status;

	@NotBlank
	@Column(nullable = false)
	private int quantity;
	
	@NotBlank
	@Column(nullable = false)
	private double price;


	private Date addedDate;

	@NotBlank
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "dealer_id", referencedColumnName="ID", nullable = false)
	@ForeignKey(name = "Fk_dealeritems_user")
	private User itemDealer;

	@NotBlank
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "store_id", referencedColumnName="ID", nullable = false)
	@ForeignKey(name = "Fk_storeitems_user")
	private Store itemStore;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private SType type;

	@Enumerated(EnumType.ORDINAL)
	private ItemRate rate;

	// in case user is dealer thin he have item in the store
	@JsonIgnore
	@OneToMany(mappedBy = "imageItem", cascade = CascadeType.ALL)
	Set<ItemImages> images = new HashSet();
	
	// in case user is dealer thin he have item in the carts
	@JsonIgnore
	@OneToMany(mappedBy = "cartItem", cascade = CascadeType.ALL)
	Set<CartItems> itemCarts = new HashSet();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Set<ItemImages> getImages() {
		return images;
	}

	public void setImages(Set<ItemImages> images) {
		this.images = images;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public User getItemDealer() {
		return itemDealer;
	}

	public void setItemDealer(User itemDealer) {
		this.itemDealer = itemDealer;
	}

	public Store getItemStore() {
		return itemStore;
	}

	public void setItemStore(Store itemStore) {
		this.itemStore = itemStore;
	}

	public SType getType() {
		return type;
	}

	public void setType(SType type) {
		this.type = type;
	}

	public ItemRate getRate() {
		return rate;
	}

	public void setRate(ItemRate rate) {
		this.rate = rate;
	}

	public Set<CartItems> getItemCarts() {
		return itemCarts;
	}

	public void setItemCarts(Set<CartItems> itemCarts) {
		this.itemCarts = itemCarts;
	}

	@Override
	public String toString() {
		return "Item [itemId=" + id + ", itemName=" + itemName + ", description=" + description + ", status="
				+ status + ", quantity=" + quantity + ", price="+ price + ", addedDate=" + addedDate + ", dealer=" + itemDealer + ", store="
				+ itemStore + ", type=" + type + ", rate=" + rate + "]";
	}

}
