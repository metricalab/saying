package com.metricalab.saying.pojo;

public class SayingDTO {

	private Long id;
	private String text;
	private int quality;
	private String origin;

	public SayingDTO(Long id, String text, Integer quality, String origin) {
		this.text = text;
		this.quality = quality;
		this.origin = origin;
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

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
