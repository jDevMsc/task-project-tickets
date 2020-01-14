package org.germes.presentation.admin.bean;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

@Named
@RequestScoped
/**
 * Managed bean that keeps all the cities for the main page
 */
public class CitiesBean {

	private final List<CityBean> cities;
	
	public CitiesBean() {
		cities = new ArrayList<>();
		cities.add(new CityBean("Odessa", "", "Odessa"));
		cities.add(new CityBean("Izmail", "", "Odessa"));
	}

	public List<CityBean> getCities() {
		return cities;
	}
}
