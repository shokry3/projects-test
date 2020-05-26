package com.app.shop.model.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email") })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userid_Sequence")
	private long id;

	@NotBlank
	@Column(nullable = false)
	private String username;

	@NotBlank
	@Column(nullable = false)
	private String password;

	@Column(length = 50)
	private String fullname;

	@NotBlank
	@Column(nullable = false)
	private String mobile;

	@NotBlank
	@Email
	@Column(nullable = false)
	private String email;

	@Column(nullable = true)
	private int postalcode;

	@Size(min = 30, max = 200)
	private String address;
	
	@Column(nullable = true)
	private boolean enabled = true;
	
	@Column(nullable = true)
	private boolean blocked = false;

	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	//@JsonIgnore
	@OneToMany(mappedBy = "interstedUser", cascade = CascadeType.ALL)
	Set<UserInterests> interests = new HashSet();

	// in case user is dealer thin he have item of the dealer
	//@JsonIgnore
	@OneToMany(mappedBy = "itemDealer", cascade = CascadeType.ALL)
	Set<Item> item = new HashSet();

	// in case user is dealer thin he have cart item of the carts
	@JsonIgnore
	@OneToMany(mappedBy = "cartUser", cascade = CascadeType.ALL)
	Set<ShoppingCart> userCarts = new HashSet();

	@Lob
	@Column(name = "photo", nullable = true)
	private byte[] photo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
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

	public int getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(int postalcode) {
		this.postalcode = postalcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isBlocked() {
		return blocked;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<UserInterests> getInterests() {
		return interests;
	}

	public void setInterests(Set<UserInterests> interests) {
		this.interests = interests;
	}

	public Set<Item> getItems() {
		return item;
	}

	public void setItems(Set<Item> item) {
		this.item = item;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public Set<ShoppingCart> getUserCarts() {
		return userCarts;
	}

	public void setUserCarts(Set<ShoppingCart> userCarts) {
		this.userCarts = userCarts;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", fullname=" + fullname
				+ ", mobile=" + mobile + ", email=" + email + ", postalcode=" + postalcode + ", address=" + address
				+ ", enabled=" + enabled + ", blocked=" + blocked + ", roles=" + roles + "]";
	}

}
