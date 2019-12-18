package org.tickets.germes.app.rest.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.math.NumberUtils;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.entity.transport.TransportType;
import org.tickets.germes.app.rest.dto.CityDTO;
import org.tickets.germes.app.rest.service.base.BaseResource;
import org.tickets.germes.app.service.GeographicService;
import org.tickets.germes.app.service.impl.GeographicServiceImpl;
import org.tickets.germes.app.service.transform.Transformer;
import org.tickets.germes.app.service.transform.impl.SimpleDTOTransformer;

/**
 *  CityResource is REST web-service that handles city-related requests
 */
@Path("/cities")
public class CityResource extends BaseResource {

	/**
	 * Underlying source of data
	 */
	private final GeographicService service;

	/**
	 * DTO <-> Entity transformer
	 */
	private final Transformer transformer;

	public CityResource() {
		transformer = new SimpleDTOTransformer();

		service = new GeographicServiceImpl();
		City city = new City("Odessa");
		city.addStation(TransportType.AUTO);
		service.saveCity(city);
	}
	/**
	 * Returns all the existing cities
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CityDTO> findCities() {
		return service.findCities().stream().map((city) -> transformer.transform(city, CityDTO.class))
				.collect(Collectors.toList());
	}
	/**
	 * Saves new city instance
	 * @return
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void saveCity(CityDTO cityDTO) {
		service.saveCity(transformer.untransform(cityDTO, City.class));
	}
	/**
	 * Returns city with specified identifier
	 * @return
	 */
	@Path("/{cityId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findCityById(@PathParam("cityId") final String cityId) {
		if(!NumberUtils.isNumber(cityId)) {
			return BAD_REQUEST;
		}
		
		Optional<City> city = service.findCitiyById(NumberUtils.toInt(cityId));
		if (!city.isPresent()) {
			return NOT_FOUND;
		}
		return ok(transformer.transform(city.get(), CityDTO.class));
	}

}
