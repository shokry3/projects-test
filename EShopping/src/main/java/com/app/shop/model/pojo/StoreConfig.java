package com.app.shop.model.pojo;

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

import org.hibernate.annotations.ForeignKey;

import com.app.shop.model.enums.Currency;
import com.app.shop.model.enums.Locales;

@Entity
@Table(name = "STORE_CONFIGS")
public class StoreConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "configid_Sequence")
	private int id;

	@ManyToOne
	@JoinColumn(name = "store_id", referencedColumnName = "ID", nullable = false)
	@ForeignKey(name = "Fk_storeconfig_store")
	private Store storeConfig;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Locales locale;

	private Float vat;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Currency currency;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Store getStoreConfig() {
		return storeConfig;
	}

	public void setStoreConfig(Store storeConfig) {
		this.storeConfig = storeConfig;
	}

	public Locales getLocale() {
		return locale;
	}

	public void setLocale(Locales locale) {
		this.locale = locale;
	}

	public Float getVat() {
		return vat;
	}

	public void setVat(Float vat) {
		this.vat = vat;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public String toString() {
		return "StoreConfig [id=" + id + ", storeConfig=" + storeConfig + ", locale=" + locale + ", vat=" + vat
				+ ", currency=" + currency + "]";
	}

}
