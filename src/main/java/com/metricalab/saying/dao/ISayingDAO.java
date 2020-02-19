package com.metricalab.saying.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.metricalab.saying.entity.Saying;

public interface ISayingDAO extends JpaRepository<Saying, Long> {

	Optional<Saying> findFirstByOrderByQualityDesc();

	@Query(value = "SELECT * FROM sayings ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Optional<Saying> findRandom();

	Optional<List<Saying>> findTop5ByOrderByQuality();

	Optional<List<Saying>> findTop10ByOrderByQuality();

	Optional<List<Saying>> findAllByOrderByQualityDesc();

	Optional<List<Saying>> findAllByOrderByQualityAsc();

	Optional<List<Saying>> findByOrigin(String origin);

	Optional<List<Saying>> findByTextContainingIgnoreCase(String text);

	Optional<List<Saying>> findByOriginContainingIgnoreCase(String origin);

	Optional<List<Saying>> findByOriginContainingIgnoreCaseOrderByOriginDesc(String origin);

	Optional<List<Saying>> findByOriginContainingIgnoreCaseOrderByOriginAsc(String origin);

	@Query(value = "SELECT AVG(quality) FROM sayings", nativeQuery = true)
	public Double avg();
}
