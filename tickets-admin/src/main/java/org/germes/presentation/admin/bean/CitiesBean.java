package org.germes.presentation.admin.bean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.service.GeographicService;

@Named
@RequestScoped
/**
 * Managed bean that keeps all the cities for the main page
 */
public class CitiesBean {
    private final GeographicService geographicService;
    @Inject
    public CitiesBean(GeographicService geographicService) {
        this.geographicService = geographicService;
    }

    public List<City> getCities() {
        return geographicService.findCities();
    }
}