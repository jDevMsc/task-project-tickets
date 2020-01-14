package org.tickets.germes.app.service;

import java.util.List;
import java.util.Optional;

import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.search.criteria.StationCriteria;
import org.tickets.germes.app.model.search.criteria.range.RangeCriteria;

/**
 * Entry point to perform operations over geographic entities
 */
public interface GeographicService {

	/**
	 * Returns list of existing cities
	 */
	List<City> findCities();

	/**
	 * Returns city with specified identifier. If no city is found then empty optional is
	 * returned
	 */
	Optional<City> findCitiyById(int id);

	/**
	 * Returns all the stations that match specified criteria
	 */
	List<Station> searchStations(StationCriteria criteria, RangeCriteria rangeCriteria);

	/**
	 * Saves specified city instance
	 */
	void saveCity(City city);

	/**
	 * Removes all the cities
	 */
	void deleteCities();
	/**
	 * Delete city with specified identifier
	 */
	void deleteCity(int cityId);
}
