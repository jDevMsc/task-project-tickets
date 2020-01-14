package org.germes.presentation.admin.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import org.tickets.germes.app.model.entity.geography.City;

/**
 * CityBean is value holder of the city data
 * for admin project
 */
@ManagedBean(name="currentCity")
@ViewScoped
public class CityBean extends City {
    public void clear() {
        setName("");
        setDistrict("");
        setRegion("");
    }

    public void update(City city) {
        setName(city.getName());
        setDistrict(city.getDistrict());
        setRegion(city.getRegion());
        setId(city.getId());
    }
}
