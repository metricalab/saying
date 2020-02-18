package com.metricalab.saying.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "sayings")
public class Saying extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = -2955850841909139794L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String text;

	@Column(nullable = false)
	private Integer quality;

	@Column(name = "last_update")
	@Temporal(TemporalType.DATE)
	private Date lastUpdate;

	@Column(nullable = false)
	private String user;

	public Saying() {

	}

	public Saying(String text, int quality, Date lastUpdate, String user) {
		this.text = text;
		this.quality = quality;
		this.lastUpdate = lastUpdate;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date updated) {
		this.lastUpdate = updated;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
