package com.agiletrailbrazers.weather.domain;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by gustavosousa on 9/16/17.
 */
@Embeddable
public class Geolocation implements Serializable {

    private double latitude;
    private double longitude;

    public Geolocation() {
    }

    public Geolocation(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
