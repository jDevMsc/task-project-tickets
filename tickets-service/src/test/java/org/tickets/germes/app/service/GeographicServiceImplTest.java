package org.tickets.germes.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.tickets.germes.app.infra.exception.flow.ValidationException;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.geography.Station;
import org.tickets.germes.app.model.entity.transport.TransportType;
import org.tickets.germes.app.model.search.criteria.StationCriteria;
import org.tickets.germes.app.model.search.criteria.range.RangeCriteria;
import org.tickets.germes.app.persistance.hibernate.SessionFactoryBuilder;
import org.tickets.germes.app.persistance.repository.CityRepository;
import org.tickets.germes.app.persistance.repository.StationRepository;
import org.tickets.germes.app.persistance.repository.hibernate.HibernateCityRepository;
import org.tickets.germes.app.persistance.repository.hibernate.HibernateStationRepository;
import org.tickets.germes.app.persistance.repository.inmemory.InMemoryCityRepository;
import org.tickets.germes.app.service.impl.GeographicServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Contain unit-tests for  GeographicServiceImpl
 */
public class GeographicServiceImplTest {
	private static final int DEFAULT_CITY_ID = 1;

	private static GeographicService service;

	private static ExecutorService executorService;

	@Before
	public void setup() {
		SessionFactoryBuilder builder = new SessionFactoryBuilder();
		CityRepository repository = new HibernateCityRepository(builder);
		StationRepository stationRepository = new HibernateStationRepository(builder);
		service = new GeographicServiceImpl(repository, stationRepository);

		executorService = Executors.newCachedThreadPool();
	}

	@Test
	public void testNoDataReturnedAtStart() {
		List<City> cities = service.findCities();
		assertTrue(cities.isEmpty());
	}

	@Test
	public void testSaveNewCitySuccess() {
		City city = new City("Ivanovo");
		service.saveCity(city);

		List<City> cities = service.findCities();
		assertEquals(cities.size(), 1);
		assertEquals(cities.get(0).getName(), "Ivanovo");
	}

	@Test
	public void testFindCityByIdSuccess() {
		City city = new City("Ivanovo");
		service.saveCity(city);

		Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
		assertTrue(foundCity.isPresent());
		assertEquals(foundCity.get().getId(), DEFAULT_CITY_ID);
	}

	@Test
	public void testFindCityByIdNotFound() {
		Optional<City> foundCity = service.findCitiyById(DEFAULT_CITY_ID);
		assertFalse(foundCity.isPresent());
	}

	@Test
	public void testSearchStationsByNameSuccess() {
		City city = new City("Ivanovo");
		city.setId(DEFAULT_CITY_ID);
		city.addStation(TransportType.AUTO);
		city.addStation(TransportType.RAILWAY);
		service.saveCity(city);

		List<Station> stations = service.searchStations(StationCriteria.byName("Ivanovo"), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertEquals(stations.size(), 2);
		assertEquals(stations.get(0).getCity(), city);
	}

	@Test
	public void testSearchStationsByNameNotFound() {
		List<Station> stations = service.searchStations(StationCriteria.byName("Ivanovo"), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertTrue(stations.isEmpty());
	}
	
	@Test
	public void testSearchStationsByTransportTypeSuccess() {
		City city = new City("Ivanovo");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Moscow");
		city2.setId(2);
		city2.addStation(TransportType.AUTO);
		service.saveCity(city2);
		
		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AUTO), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertEquals(stations.size(), 2);
	}
	
	@Test
	public void testSearchStationsByTransportTypeNotFound() {
		City city = new City("Ivanovo");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
		City city2 = new City("Moscow");
		city2.setId(2);
		city2.addStation(TransportType.RAILWAY);
		service.saveCity(city2);
		
		List<Station> stations = service.searchStations(new StationCriteria(TransportType.AVIA), new RangeCriteria(1, 5));
		assertNotNull(stations);
		assertTrue(stations.isEmpty());
	}


	/**
	 * Multithreading testing
	 */
	@Test
	public void testSaveMultipleCitiesSuccess() {
		int cityCount = service.findCities().size();

		int addedCount = 100_000;
		for (int i = 0; i < addedCount; i++) {
			City city = new City("Ivanovo" + i);
			city.setDistrict("Ivanovo");
			city.setRegion("Ivanovo");
			city.addStation(TransportType.AUTO);
			service.saveCity(city);
		}

		List<City> cities = service.findCities();
		assertEquals(cities.size(), cityCount + addedCount);
	}

	/**
	 * add 2000 entries in 200 threads
	 */
	@Test
	public void testSaveMultipleCitiesConcurrentlySuccess() {
		int cityCount = service.findCities().size();

		int threadCount = 200;
		int batchCount = 10;

		List<Future<?>> futures = new ArrayList<>();

		for (int i = 0; i < threadCount; i++) {
			futures.add(executorService.submit(() -> {
				for (int j = 0; j < batchCount; j++) {
					City city = new City("Lviv_" + Math.random());
					city.setDistrict("Lviv");
					city.setRegion("Lviv");
					city.addStation(TransportType.AUTO);
					service.saveCity(city);
				}
			}));
		}

		waitForFutures(futures);

		List<City> cities = service.findCities();
		assertEquals(cities.size(), cityCount + threadCount * batchCount);
	}
	/**
	 * Change 1 entries in 200 threadsGeographicServiceImpl
	 */
	@Test
	public void testSaveOneCityConcurrentlySuccess() {
		City city = new City("Voronezh");
		city.setDistrict("Voronezh");
		city.setRegion("Voronezh");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);

		int cityCount = service.findCities().size();

		int threadCount = 200;

		List<Future<?>> futures = new ArrayList<>();

		for (int i = 0; i < threadCount; i++) {
			futures.add(executorService.submit(() -> {
				city.setName("Voronezh" + Math.random());
				service.saveCity(city);
			}));
		}

		waitForFutures(futures);

		List<City> cities = service.findCities();
		assertEquals(cities.size(), cityCount);
	}

	private void waitForFutures(List<Future<?>> futures) {
		futures.forEach(future -> {
			try {
				future.get();
			} catch (Exception e) {
				fail(e.getMessage());
			}
		});
	}

	private City createCity() {
		City city = new City("Ivanovo");
		city.setDistrict("Ivanovo");
		city.setRegion("Ivanovo");

		return city;
	}

	@Test
	public void testSaveCityMissingNameValidationExceptionThrown() {
		try {
			City city = new City();
			city.setDistrict("Ivanovo");
			city.setRegion("Ivanovo");
			service.saveCity(city);

			fail("City name validation failed");
		} catch (ValidationException ex) {
			assertTrue(ex.getMessage().contains("name:may not be null"));
		}
	}

	@Test
	public void testSaveCityNameTooShortValidationExceptionThrown() {
		try {
			City city = new City("N");
			city.setDistrict("Ivanovo");
			city.setRegion("Ivanovo");
			service.saveCity(city);

			fail("City name validation failed");
		} catch (ValidationException ex) {
			assertTrue(ex.getMessage().contains("name:size must be between 2 and 32"));
		}
	}

	@Test
	public void testSaveCityNameTooLongValidationExceptionThrown() {
		try {
			City city = new City("N1234567823456789012345678901234567890");
			city.setDistrict("Ivanovo");
			city.setRegion("Ivanovo");
			service.saveCity(city);

			fail("City name validation failed");
		} catch (ValidationException ex) {
			assertTrue(ex.getMessage().contains("name:size must be between 2 and 32"));
		}
	}
}
