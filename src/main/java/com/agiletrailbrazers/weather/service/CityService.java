package com.agiletrailbrazers.weather.service;

import com.agiletrailbrazers.weather.domain.report.ReportTemperatureDifference;
import com.agiletrailbrazers.weather.exception.CityNotFoundException;

/**
 * Created by gustavosousa on 9/16/17.
 */
public interface CityService {
    ReportTemperatureDifference findDifferenceTemperature(double latitude1, double longitude1, double latitude2, double longitude2) throws CityNotFoundException;
}
