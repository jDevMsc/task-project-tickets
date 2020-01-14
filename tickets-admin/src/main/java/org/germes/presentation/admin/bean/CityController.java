package org.germes.presentation.admin.bean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.service.GeographicService;

@Named
@ApplicationScoped
/**
 * Managed bean that keeps all the cities for the main page
 */
public class CityController {
    private final GeographicService geographicService;
    @Inject
    public CityController(GeographicService geographicService) {
        this.geographicService = geographicService;
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }

    public void saveCity(CityBean cityBean) {
        City city = new City();
        city.setName(cityBean.getName());
        city.setRegion(cityBean.getRegion());
        city.setDistrict(cityBean.getDistrict());
        city.setId(cityBean.getId());
        geographicService.saveCity(city);
    }
    public void delete(int cityId) {
        geographicService.deleteCity(cityId);
    }
}