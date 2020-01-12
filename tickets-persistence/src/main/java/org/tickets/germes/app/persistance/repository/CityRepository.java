package org.tickets.germes.app.persistance.repository;

import java.util.List;
import org.tickets.germes.app.model.entity.geography.City;

/**
 *  CRUD methods to access City objects in the persistent storage
 */
public interface CityRepository {
    /**
     * Saves(creates or modifies)  city
     */
    void save(City city);

    /**
     * Saves specified cities
     */
    void saveAll(List<City> cities);

    /**
     * Returns city . If no city exists with  null is returned
     */
    City findById(int cityId);

    /**
     * Delete city with specified identifier
     */
    void delete(int cityId);

    /**
     * Deletes all the cities
     */
    void deleteAll();

    /**
     * Returns all the cities
     */
    List<City> findAll();



}