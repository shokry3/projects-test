package com.app.shop.model.pojo;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.app.shop.model.enums.ERole;

@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "roleid_Sequence")
	private Integer id;

	@Enumerated(EnumType.STRING)
	@NotBlank
	@Column(length = 20, nullable = false)
	private ERole name;

	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}