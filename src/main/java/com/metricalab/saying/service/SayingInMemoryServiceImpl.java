package com.metricalab.saying.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.metricalab.saying.pojo.InMemorySaying;
import com.metricalab.saying.pojo.SayingDTO;
import com.metricalab.saying.utils.ConstantsData;

@Service
@Qualifier("inMemory")
public class SayingInMemoryServiceImpl implements ISayingService {

	private static final Logger log = Logger.getLogger(SayingInMemoryServiceImpl.class.getName());

	@Autowired
	private InMemorySaying inMemorySaying;

	@Override
	public SayingDTO getBestSaying() {
		log.log(Level.INFO, "Obteniendo el mejor refrán");
		return Collections.max(inMemorySaying.getSaying(), Comparator.comparing(c -> c.getQuality()));
	}

	@Override
	public SayingDTO getRandomSaying() {
		log.log(Level.INFO, "Obteniendo un refrán aleatorio");
		return inMemorySaying.getSaying().get(new Random().nextInt(inMemorySaying.getMaxSaying() - 1));
	}

	@Override
	public List<SayingDTO> getSaying(final int numberSaying, final String order) {
		log.log(Level.INFO, () -> String.format("Obteniendo %s refranes pedidos. Criterio de ordenación: %s. ",
				numberSaying, order));
		final Stream<SayingDTO> str = sortSayingList(order, inMemorySaying.getSaying()).stream();
		return str.limit(numberSaying).collect(Collectors.toList());
	}

	@Override
	public Integer getNumSaying() {
		log.log(Level.INFO, "Obteniendo el número de refranes disponibles");
		return inMemorySaying.getSaying().size();
	}

	@Override
	public SayingDTO addSaying(final SayingDTO saying) {
		log.log(Level.INFO, "Insertando un nuevo refrán: {0}", saying.getText());
		final Long maxValue = inMemorySaying.getSaying().stream().mapToLong(x -> x.getId()).max().getAsLong();
		saying.setId(maxValue + 1);
		inMemorySaying.getSaying().add(saying);
		return saying;
	}

	@Override
	public List<SayingDTO> sortSaying(final String order) {
		log.log(Level.INFO, "Ordenando refranes: {0} ", order);
		final List<SayingDTO> saying = inMemorySaying.getSaying();
		sortSayingList(order, saying);
		return saying;
	}

	@Override
	public void deleteSaying(final Long id) {
		final List<SayingDTO> saying = inMemorySaying.getSaying();
		saying.removeIf(x -> x.getId().equals(id));
	}

	/**
	 * Ordena una lista de refranes
	 *
	 * @param order    tipo de ordenación
	 * @param saying Lista de refranes
	 * @return Saying ordenados
	 */
	private List<SayingDTO> sortSayingList(final String order, final List<SayingDTO> saying) {

		if (ConstantsData.ASCENDENTE.equalsIgnoreCase(order)) {
			Collections.sort(saying,
					Comparator.comparing(p -> p.getQuality(), Comparator.nullsLast(Comparator.naturalOrder())));
		} else {
			Collections.sort(saying,
					Comparator.comparing(p -> p.getQuality(), Comparator.nullsLast(Comparator.reverseOrder())));
		}
		return saying;
	}

	@Override

	public SayingDTO getSayingById(final Long id) {
		log.log(Level.INFO, "Buscar refrán con id {0}", id);
		final List<SayingDTO> sayings = inMemorySaying.getSaying();
		final SayingDTO result = sayings.stream().filter(saying -> id.equals(saying.getId())).findAny().orElse(null);
		return result;
	}

	@Override
	public List<SayingDTO> getSayingByUser(final String user) {
		log.log(Level.INFO, "Buscar refrán por el username {0}", user);
		final List<SayingDTO> sayings = inMemorySaying.getSaying();
		final List<SayingDTO> result = sayings.stream().filter(saying -> saying.getUser().equals(user))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<SayingDTO> getContainsSaying(String text) {
		final List<SayingDTO> sayings = inMemorySaying.getSaying();
		return sayings.stream().filter(saying -> saying.getText().toLowerCase().contains(text.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<SayingDTO> getContainsUser(String user) {
		log.log(Level.INFO, () -> String.format("Obteniendo usuario %s ", user));
		final List<SayingDTO> sayings = inMemorySaying.getSaying();
		return sayings.stream().filter(saying -> saying.getUser().toLowerCase().contains(user.toLowerCase()))
				.collect(Collectors.toList());
	}

	@Override
	public List<SayingDTO> getContainsUserOrder(String user, String order) {
		log.log(Level.INFO,
				() -> String.format("Obteniendo usuario %s . Criterio de ordenación: %s. ", user, order));
		final List<SayingDTO> searchResult = getContainsUser(user);
		return sortSayingList(order, searchResult);
	}

	@Override
	public Double getAvgQualitySaying() {
		log.log(Level.INFO, "Obteniendo la media de calidad de los refranes");
		final OptionalDouble avg = inMemorySaying.getSaying().stream().mapToDouble(x -> x.getQuality()).average();
		return avg.orElse(0);
	}

}
