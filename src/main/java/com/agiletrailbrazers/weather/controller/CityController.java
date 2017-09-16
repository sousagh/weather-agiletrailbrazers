package com.agiletrailbrazers.weather.controller;

import com.agiletrailbrazers.weather.domain.report.ReportTemperatureDifference;
import com.agiletrailbrazers.weather.exception.CityNotFoundException;
import com.agiletrailbrazers.weather.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gustavosousa on 9/16/17.
 */
@RestController
public class CityController {

    private CityService service;

    @Autowired
    public CityController(CityService service) {
        this.service = service;
    }

    @RequestMapping(value = "tempDiff", method = RequestMethod.GET)
    public ReportTemperatureDifference tempDiff(@RequestParam(value = "lat1") double latitude1,
                                                @RequestParam(value = "long1") double longitude1,
                                                @RequestParam(value = "lat2") double latitude2,
                                                @RequestParam(value = "long2") double longitude2) throws CityNotFoundException {

        return this.service.findDifferenceTemperature(latitude1, longitude1, latitude2, longitude2);
    }
}
