package org.tickets.germes.app.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.tickets.germes.app.model.entity.geography.City;
import org.tickets.germes.app.rest.dto.base.BaseDTO;

/**
 * Holds city state for the client-server communication , used as return value
 */
@ApiModel(description = "City with transport stations and purchase tickets")
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

	@ApiModelProperty(name = "Name of the city", required = true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ApiModelProperty(name = "Name of the city's district. Empty for region center")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@ApiModelProperty(name = "Name of the city's region", required = true     )
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
}
