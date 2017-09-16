package com.agiletrailbrazers.weather.domain.report;

import com.agiletrailbrazers.weather.domain.City;

import java.io.Serializable;

/**
 * Created by gustavosousa on 9/16/17.
 */
public class ReportTemperatureDifference implements Serializable {

    private City city1;
    private City city2;
    private double temperatureDifference;

    public ReportTemperatureDifference(City city1, City city2) {
        this.city1 = city1;
        this.city2 = city2;

        this.temperatureDifference = city1.getTemperature() - city2.getTemperature();
    }

    public double getTemperatureDifference() {
        return this.temperatureDifference;
    }

    public City getCity1() {
        return this.city1;
    }

    public City getCity2() {
        return this.city2;
    }
}
