package com.metricalab.saying.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.metricalab.saying.converter.SayingConverter;
import com.metricalab.saying.dao.ISayingDAOService;
import com.metricalab.saying.entity.Saying;
import com.metricalab.saying.pojo.SayingDTO;

@Service
@Qualifier("dataBase")
public class SayingDataBaseServiceImpl implements ISayingService {

	private static final Logger log = Logger.getLogger(SayingDataBaseServiceImpl.class.getName());

	@Autowired
	private ISayingDAOService sayingDAOService;

	@Autowired
	private SayingConverter sayingConverter;

	@Override
	public SayingDTO getBestSaying() {
		log.log(Level.INFO, "Obteniendo el mejor refrán");
		return sayingConverter.convert(sayingDAOService.getBestSaying().orElse(null));
	}

	@Override
	public SayingDTO getRandomSaying() {
		log.log(Level.INFO, "Obteniendo un refrán aleatorio");
		return sayingConverter.convert(sayingDAOService.getRandomSaying().orElse(null));
	}

	@Override
	public List<SayingDTO> getSaying(final int numSaying, final String order) {
		log.log(Level.INFO, "Obteniendo {0} refranes pedidos.", numSaying);
		return sayingConverter.convert(sayingDAOService.getSaying(numSaying, order).orElse(null));
	}

	@Override
	public Integer getNumSaying() {
		log.log(Level.INFO, "Obteniendo el número de refranes disponibles");
		return Math.toIntExact(sayingDAOService.getNumSaying());
	}

	@Override
	public SayingDTO addSaying(final SayingDTO sayingDTO) {
		log.log(Level.INFO, "Insertando un nuevo refrán: {0}", sayingDTO.getText());
		final Saying saying = sayingDAOService.addSaying(sayingConverter.apply(sayingDTO));
		return sayingConverter.apply(saying);
	}

	@Override
	public List<SayingDTO> sortSaying(final String order) {
		log.log(Level.INFO, "Ordenando refranes: {0} ", order);
		return sayingConverter.convert(sayingDAOService.sortSaying(order).orElse(null));
	}

	@Override
	public void deleteSaying(final Long id) {
		log.log(Level.INFO, "Borrar refrán con id: {0} ", id);
		sayingDAOService.deleteSaying(id);
	}

	@Override
	public SayingDTO getSayingById(final Long id) {
		log.log(Level.INFO, "Buscar refrán con id {0}", id);
		return sayingConverter.convert(sayingDAOService.getSayingById(id).orElse(null));
	}

	@Override
	public List<SayingDTO> getSayingByOrigin(final String origin) {
		log.log(Level.INFO, "Buscar refrán por el username {0}", origin);
		return sayingConverter.convert(sayingDAOService.getSayingByOrigin(origin).orElse(null));
	}

	@Override
	public List<SayingDTO> getContainsSaying(final String text) {
		log.log(Level.INFO, "Buscar Refrán", text);
		return sayingConverter.convert(sayingDAOService.getSayingContains(text).orElse(null));

	}

	@Override
	public List<SayingDTO> getContainsOrigin(final String origin) {
		log.log(Level.INFO, "Buscar Usuario", origin);
		return sayingConverter.convert(sayingDAOService.getSayingOriginContains(origin).orElse(null));
	}

	@Override
	public List<SayingDTO> getContainsOriginOrder(final String origin, final String order) {
		log.log(Level.INFO, "Buscar Usuario %s", origin);
		log.log(Level.INFO, "Ordenado de manera %s ", order);
		return sayingConverter
				.convert(sayingDAOService.getSayingOriginContainsOrder(origin, order).orElse(null));

	}

	@Override
	public Double getAvgQualitySaying() {
		log.log(Level.INFO, "Obteniendo la media de la calidad de los refranes disponibles");
		return sayingDAOService.getAvgQualitySaying();
	}

}
