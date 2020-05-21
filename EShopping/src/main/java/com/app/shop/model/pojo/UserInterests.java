package com.app.shop.model.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ForeignKey;

import com.app.shop.model.enums.SType;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER_INTERESTS", uniqueConstraints = { @UniqueConstraint(columnNames = {"ITEM_ID", "USER_ID"})})
public class UserInterests {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "interestid_Sequence")
	private long id;
	
	private Date addedDate;
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(length = 30, nullable = false)
	private SType InterestType;
	
	@NotBlank
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name ="item_id", referencedColumnName="ID", nullable = false)
	@ForeignKey(name="Fk_iteminterests_Item")
    private Item interstedItem;
	
	//@NotBlank
	@ManyToOne
	@JsonIgnore
    @JoinColumn(name ="user_id", referencedColumnName="ID") //, nullable = false
	@ForeignKey(name="Fk_userdinterests_user")
    private User interstedUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public SType getInterestType() {
		return InterestType;
	}

	public void setInterestType(SType interestType) {
		InterestType = interestType;
	}

	public User getInterstedUser() {
		return interstedUser;
	}

	public void setInterstedUser(User interstedUser) {
		this.interstedUser = interstedUser;
	}

	public Item getInterstedItem() {
		return interstedItem;
	}

	public void setInterstedItem(Item interstedItem) {
		this.interstedItem = interstedItem;
	}

	@Override
	public String toString() {
		return "UserInterests [id=" + id + ", addedDate=" + addedDate + ", InterestType=" + InterestType
				+ ", userId=" + interstedUser + ", interstedItem=" + interstedItem +  "]";
	}
	
}
