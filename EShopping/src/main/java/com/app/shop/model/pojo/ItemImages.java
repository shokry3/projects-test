package com.app.shop.model.pojo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ITEM_IMAGES")
public class ItemImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageid_Sequence")
	private long id;
	
	//@NotBlank
	@Column(nullable = true)
	private String description;
	
	@NotNull
	@Lob
	@Column(nullable = false)
	private byte[] image;	
	
	@NotNull
	@Column(name = "item_id",nullable = false)
//	@ManyToOne
//	@JsonIgnore
//	@JoinColumn(name = "item_id", referencedColumnName="ID", nullable = false)
//	@ForeignKey(name = "Fk_itemimages_item")
	private long imageItem;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public long getImageItem() {
		return imageItem;
	}

	public void setImageItem(long imageItem) {
		this.imageItem = imageItem;
	}

	@Override
	public String toString() {
		return "ItemImages [id=" + id + ", description=" + description + ", image=" + image + ", imageItem=" + imageItem
				+ "]";
	}

}
