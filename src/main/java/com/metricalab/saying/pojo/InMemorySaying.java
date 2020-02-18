package com.metricalab.saying.pojo;

import java.util.List;

public class InMemorySaying {

	private List<SayingDTO> saying;
	private int maxSaying = 15;

	public List<SayingDTO> getSaying() {
		return saying;
	}

	public void setSaying(List<SayingDTO> saying) {
		this.saying = saying;
	}

	public int getMaxSaying() {
		return maxSaying;
	}

	public void setMaxSaying(int maxSaying) {
		this.maxSaying = maxSaying;
	}

}
