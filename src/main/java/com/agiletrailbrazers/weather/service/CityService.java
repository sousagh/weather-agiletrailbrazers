package com.agiletrailbrazers.weather.service;

import com.agiletrailbrazers.weather.domain.report.ReportTemperatureDifference;
import com.agiletrailbrazers.weather.exception.CityNotFoundException;

/**
 * Created by gustavosousa on 9/16/17.
 */
public interface CityService {
    ReportTemperatureDifference findDifferenceTemperature(double lat1, double long1, double lat2, double long2) throws CityNotFoundException;
}
