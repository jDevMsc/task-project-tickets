package org.germes.presentation.admin.bean;

import static org.junit.Assert.*;

import org.junit.Test;
import org.tickets.germes.app.model.entity.geography.City;

public class CityBeanTest {
    @Test
    public void clear_beanInitialized_allFieldsCleared() {
        CityBean cityBean = new CityBean();
        cityBean.setDistrict("test");
        cityBean.setId(1);
        cityBean.setName("test");
        cityBean.setRegion("test");
        cityBean.clear();
        assertTrue(StringUtils.isEmpty(cityBean.getDistrict()));
        assertTrue(StringUtils.isEmpty(cityBean.getName()));
        assertTrue(StringUtils.isEmpty(cityBean.getRegion()));
        assertTrue(cityBean.getId() == 0);
    }

    @Test
    public void untransform_cityInitialized_cityReturned() {
        City city = new City();
        CityBean cityBean = new CityBean();
        City newCity = cityBean.untransform(city);
        assertSame(city, newCity);
    }
}