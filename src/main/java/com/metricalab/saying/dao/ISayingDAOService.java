package com.metricalab.saying.dao;

import java.util.List;
import java.util.Optional;

import com.metricalab.saying.entity.Saying;

public interface ISayingDAOService {

	public Optional<Saying> getBestSaying();

	public Optional<Saying> getRandomSaying();

	public Optional<List<Saying>> getSaying(int numSaying, final String order);

	public Long getNumSaying();

	public Saying addSaying(final Saying saying);

	public Optional<List<Saying>> sortSaying(final String order);

	public void deleteSaying(final Long id);

	public Optional<Saying> getSayingById(Long id);

	public Optional<List<Saying>> getSayingByUser(String user);

	public Optional<List<Saying>> getSayingContains(final String text);

	public Optional<List<Saying>> getSayingUserContains(final String user);

	public Optional<List<Saying>> getSayingUserContainsOrder(final String user, final String order);

	public Double getAvgQualitySaying();

}
