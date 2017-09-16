package com.agiletrailbrazers.weather.repository;

import com.agiletrailbrazers.weather.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gustavosousa on 9/16/17.
 */
@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    City findByGeolocationLatitudeAndGeolocationLongitude(double latitude, double longitude);
}
