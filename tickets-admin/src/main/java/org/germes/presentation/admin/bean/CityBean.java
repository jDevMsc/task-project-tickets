package org.germes.presentation.admin.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.model.transform.Transformable;

/**
 * CityBean is value holder of the city data
 * for admin project
 */
@ManagedBean(name="currentCity")
@ViewScoped
@ToString
@Getter
@Setter
public class CityBean implements Transformable<City> {

    private int id;

    private String name;
    private String district;
    private String region;

    public void clear() {
        id = 0;
        setName("");
        setDistrict("");
        setRegion("");
    }

    @Override
    public void transform(City city) {
    }

    @Override
    public City untransform(City city) {
        return city;
    }
}