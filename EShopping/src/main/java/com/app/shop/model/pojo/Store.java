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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.app.shop.model.enums.SType;

@Entity
@Table(name = "store", uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "email") })
public class Store {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "storeid_Sequence")
	private long id;

	@NotBlank
	@Column(nullable = false)
	private String name;

	@NotBlank
	@Column(nullable = false)
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(length = 30)
	private SType type;

	@Size(max = 250, min = 50)
	private String headLocation;

	private String startYear;

	private Date startDate;

	@NotBlank
	@Column(nullable = false)
	private String mobile;

	@NotBlank
	@Email
	@Column(nullable = false)
	private String email;

	// in case user is dealer thin he have item in the store
	@OneToMany(mappedBy = "itemStore", cascade = CascadeType.ALL)
	Set<Item> item = new HashSet();

	// in case user is dealer thin he have item in the store
	@OneToMany(mappedBy = "storeConfig", cascade = CascadeType.ALL)
	Set<StoreConfig> configs = new HashSet();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public SType getType() {
		return type;
	}

	public void setType(SType type) {
		this.type = type;
	}

	public String getHeadLocation() {
		return headLocation;
	}

	public void setHeadLocation(String headLocation) {
		this.headLocation = headLocation;
	}

	public String getStartYear() {
		return startYear;
	}

	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Item> getItems() {
		return item;
	}

	public void setItems(Set<Item> item) {
		this.item = item;
	}

	public Set<StoreConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(Set<StoreConfig> configs) {
		this.configs = configs;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type
				+ ", headLocation=" + headLocation + ", startYear=" + startYear + ", startDate=" + startDate
				+ ", email=" + email + "]";
	}

}
