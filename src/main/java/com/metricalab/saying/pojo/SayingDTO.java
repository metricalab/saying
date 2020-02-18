package com.metricalab.saying.pojo;

public class SayingDTO {

	private Long id;
	private String text;
	private int quality;
	private String user;

	public SayingDTO(Long id, String text, Integer quality, String user) {
		this.text = text;
		this.quality = quality;
		this.user = user;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public SayingDTO(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
