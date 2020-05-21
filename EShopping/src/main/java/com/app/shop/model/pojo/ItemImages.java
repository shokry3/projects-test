package com.app.shop.model.pojo;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ITEM_IMAGES")
public class ItemImages {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imageid_Sequence")
	private long id;
	
	@NotBlank
	@Column(nullable = false)
	private String description;
	
	@NotBlank
	@Column(nullable = false)
	private Blob image;	
	
	@NotBlank
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "item_id", referencedColumnName="ID", nullable = false)
	@ForeignKey(name = "Fk_itemimages_item")
	private Item imageItem;

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

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Item getImageItem() {
		return imageItem;
	}

	public void setImageItem(Item imageItem) {
		this.imageItem = imageItem;
	}

	@Override
	public String toString() {
		return "ItemImages [id=" + id + ", description=" + description + ", image=" + image + ", imageItem=" + imageItem
				+ "]";
	}

}
