package com.metricalab.saying.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.metricalab.saying.entity.Saying;
import com.metricalab.saying.exception.DataBaseException;
import com.metricalab.saying.utils.ConstantsData;

@Service
public class SayingDAOService implements ISayingDAOService {

	@Autowired
	private ISayingDAO repository;

	@Override
	@Transactional(readOnly = true)
	public Optional<Saying> getBestSaying() {
		return repository.findFirstByOrderByQualityDesc();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Saying> getRandomSaying() {
		return repository.findRandom();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<Saying>> getSaying(final int numSaying, final String order) {

		// Es un ejemplo de diferentes llamadas al repositorio
		// dependiendo del number de refranes a buscar
		Optional<List<Saying>> saying = null;
		if (numSaying == 5) {
			saying = repository.findTop5ByOrderByQuality();
		} else if (numSaying == 10) {
			saying = repository.findTop10ByOrderByQuality();
		} else {
			final Page<Saying> page = repository.findAll(PageRequest.of(0, numSaying,
					Sort.by(ConstantsData.ASCENDENTE.equalsIgnoreCase(order) ? Sort.Direction.ASC : Sort.Direction.DESC,
							"quality")));
			saying = Optional.of(page.get().collect(Collectors.toList()));
		}
		return saying;
	}

	@Override
	@Transactional(readOnly = true)
	public Long getNumSaying() {
		return repository.count();
	}

	@Override
	@Transactional
	public Saying addSaying(final Saying saying) {

		return repository.save(saying);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<List<Saying>> sortSaying(final String order) {

		return ConstantsData.ASCENDENTE.equalsIgnoreCase(order) ? repository.findAllByOrderByQualityAsc()
				: repository.findAllByOrderByQualityDesc();
	}

	@Override
	@Transactional
	public void deleteSaying(final Long id) {
		final Saying ref = repository.findById(id)
				.orElseThrow(() -> new DataBaseException(ConstantsData.CODE_ERR_SEARCH_SAYING,
						ConstantsData.MESS_ERR_SEARCH_SAYING, HttpStatus.NOT_FOUND));
		repository.delete(ref);
	}

	@Override
	public Optional<Saying> getSayingById(final Long id) {
		return repository.findById(id);
	}

	@Override
	public Optional<List<Saying>> getSayingByOrigin(final String origin) {
		return repository.findByOrigin(origin);

	}

	@Override
	public Optional<List<Saying>> getSayingContains(String texto) {
		return repository.findByTextContainingIgnoreCase(texto);

	}

	@Override
	public Optional<List<Saying>> getSayingOriginContains(String origin) {
		return repository.findByOriginContainingIgnoreCase(origin);

	}

	@Override
	public Optional<List<Saying>> getSayingOriginContainsOrder(String origin, String order) {

		Optional<List<Saying>> ord = null;

		if (ConstantsData.ASCENDENTE.equalsIgnoreCase(order)) {
			ord = repository.findByOriginContainingIgnoreCaseOrderByOriginAsc(origin);
		} else if (ConstantsData.DESCENDENTE.equalsIgnoreCase(order)) {
			ord = repository.findByOriginContainingIgnoreCaseOrderByOriginDesc(origin);

		}

		return ord;

	}

	@Override
	public Double getAvgQualitySaying() {
		return repository.avg();
	}
}
