package org.tickets.germes.app.rest.dto;

import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.rest.dto.base.BaseDTO;

/**
 * Holds city state for the client-server communication 
 */
public class CityDTO extends BaseDTO<City>{
	private String name;
	
	/**
	 * Name of the district where city is placed
	 */
	private String district;
	
	/**
	 * Name of the region where district is located.
	 */
	private String region;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
