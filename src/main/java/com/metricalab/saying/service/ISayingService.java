package com.metricalab.saying.service;

import java.util.List;

import com.metricalab.saying.pojo.SayingDTO;

public interface ISayingService {

	public SayingDTO getBestSaying();

	public SayingDTO getRandomSaying();

	public List<SayingDTO> getSaying(int numSaying, String order);

	public Integer getNumSaying();

	public SayingDTO addSaying(SayingDTO saying);

	public List<SayingDTO> sortSaying(String order);

	public void deleteSaying(Long id);

	public SayingDTO getSayingById(Long id);

	public List<SayingDTO> getSayingByOrigin(String origin);

	public List<SayingDTO> getContainsSaying(String text);

	public List<SayingDTO> getContainsOrigin(String origin);

	public List<SayingDTO> getContainsOriginOrder(String origin, String order);

	public Double getAvgQualitySaying();

}
