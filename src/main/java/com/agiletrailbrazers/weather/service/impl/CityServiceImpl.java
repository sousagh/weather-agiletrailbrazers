package com.agiletrailbrazers.weather.service.impl;

import com.agiletrailbrazers.weather.domain.City;
import com.agiletrailbrazers.weather.domain.report.ReportTemperatureDifference;
import com.agiletrailbrazers.weather.exception.CityNotFoundException;
import com.agiletrailbrazers.weather.repository.CityRepository;
import com.agiletrailbrazers.weather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gustavosousa on 9/16/17.
 */
@Service
public class CityServiceImpl implements CityService {

    private CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public ReportTemperatureDifference findDifferenceTemperature(double latutide1, double longitude1, double latitude2, double longitude2) throws CityNotFoundException {

        City city1 = getCity(latutide1, longitude1);

        City city2 = getCity(latitude2, longitude2);

        return new ReportTemperatureDifference(city1, city2);
    }

    private City getCity(double latitude, double longitude) throws CityNotFoundException {
        City city1 = this.cityRepository.findByGeolocationLatitudeAndGeolocationLongitude(latitude, longitude);
        if (city1 == null) {
            throw new CityNotFoundException("City with coordinates (" + latitude + ", " + longitude + ") not found.");
        }
        return city1;
    }
}
