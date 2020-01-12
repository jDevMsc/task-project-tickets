package org.tickets.germes.app.persistance.repository;

import java.util.List;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.search.criteria.StationCriteria;

/**
 * Defines CRUD methods to access Station objects in the persistent storage
 *
 */
public interface StationRepository {
	
	/**
	 * Returns all the stations that match specified criteria 
	 */
	List<Station> findAllByCriteria(StationCriteria stationCriteria);

}
