package org.tickets.germes.app.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Inject;
import org.tickets.germes.app.infra.util.CommonUtil;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.search.criteria.StationCriteria;
import org.tickets.germes.app.model.search.criteria.range.RangeCriteria;
import org.tickets.germes.app.persistance.repository.CityRepository;
import org.tickets.germes.app.persistance.repository.StationRepository;
import org.tickets.germes.app.persistance.repository.inmemory.InMemoryCityRepository;
import org.tickets.germes.app.service.GeographicService;

/**
 * Default implementation of the  GeographicService
 */
public class GeographicServiceImpl implements GeographicService {

	private final CityRepository cityRepository;

	private final StationRepository stationRepository;

	@Inject
	public GeographicServiceImpl(CityRepository cityRepository,
		StationRepository stationRepository) {
		this.cityRepository = cityRepository;
		this.stationRepository = stationRepository;
	}

	@Override
	public List<City> findCities() {
		return cityRepository.findAll();
	}

	@Override
	public void saveCity(City city) {
		cityRepository.save(city);
	}

	@Override
	public Optional<City> findCitiyById(final int id) {
		return Optional.ofNullable(cityRepository.findById(id));
	}

	@Override
	public List<Station> searchStations(final StationCriteria criteria, final RangeCriteria rangeCriteria) {
		return stationRepository.findAllByCriteria(criteria);
	}
}
