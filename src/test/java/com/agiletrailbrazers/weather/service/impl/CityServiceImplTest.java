package com.agiletrailbrazers.weather.service.impl;

import com.agiletrailbrazers.weather.domain.City;
import com.agiletrailbrazers.weather.domain.report.ReportTemperatureDifference;
import com.agiletrailbrazers.weather.exception.CityNotFoundException;
import com.agiletrailbrazers.weather.repository.CityRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by gustavosousa on 9/16/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class CityServiceImplTest {

    @Mock
    private CityRepository repository;

    @InjectMocks
    private CityServiceImpl service;

    @Test
    public void findTemperatureDifference_caseOfSuccess() throws CityNotFoundException {
        City city1 = new City();
        city1.setTemperature(100);
        Mockito.when(this.repository.findByLatitudeAndLongitude(1, 2)).thenReturn(city1);

        City city2 = new City();
        city2.setTemperature(60);
        Mockito.when(this.repository.findByLatitudeAndLongitude(2, 2)).thenReturn(city2);

        ReportTemperatureDifference differenceTemperature = this.service.findDifferenceTemperature(1, 2, 2, 2);
        Assert.assertEquals(differenceTemperature.getTemperatureDifference(), 40, 0.0001);
    }

    @Test
    public void findTemperatureDifference_equalTemperature_caseOfSuccess() throws CityNotFoundException {
        City city1 = new City();
        city1.setTemperature(100);
        Mockito.when(this.repository.findByLatitudeAndLongitude(1, 2)).thenReturn(city1);

        City city2 = new City();
        city2.setTemperature(100);
        Mockito.when(this.repository.findByLatitudeAndLongitude(2, 2)).thenReturn(city2);

        ReportTemperatureDifference differenceTemperature = this.service.findDifferenceTemperature(1, 2, 2, 2);
        Assert.assertEquals(differenceTemperature.getTemperatureDifference(), 0, 0.0001);
    }


    @Test(expected = CityNotFoundException.class)
    public void findTemperatureDifference_cityNotFound() throws CityNotFoundException {
        Mockito.when(this.repository.findByLatitudeAndLongitude(1, 2)).thenThrow(CityNotFoundException.class);
        this.service.findDifferenceTemperature(1, 2, 2, 2);
    }
}