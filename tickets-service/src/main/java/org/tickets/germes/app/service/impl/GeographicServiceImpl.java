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
import org.tickets.germes.app.persistance.repository.inmemory.InMemoryCityRepository;
import org.tickets.germes.app.service.GeographicService;

/**
 * Default implementation of the  GeographicService
 */
public class GeographicServiceImpl implements GeographicService {
	private final CityRepository cityRepository;

	@Inject
	public GeographicServiceImpl(CityRepository cityRepository) {
		this.cityRepository = cityRepository;
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
		Set<Station> stations = new HashSet<>();

		cityRepository.findAll().forEach(city -> stations.addAll(city.getStations()));

		return stations.stream().filter(station -> station.match(criteria)).collect(Collectors.toList());
	}
}
