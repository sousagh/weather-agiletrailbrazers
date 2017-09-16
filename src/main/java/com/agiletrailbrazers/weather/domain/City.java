package com.agiletrailbrazers.weather.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by gustavosousa on 9/16/17.
 */
@Entity
public class City implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Embedded
    private Geolocation geolocation;

    @Column
    private String name;

    @Column
    private double temperature;

    public City(String name, double temperature, double latitude, double longitude) {
        this.name = name;
        this.temperature = temperature;
        this.geolocation = new Geolocation(latitude, longitude);
    }

    public City() {
    }

    public long getId() {
        return this.id;
    }

    public Geolocation getGeolocation() {
        return this.geolocation;
    }

    public String getName() {
        return this.name;
    }

    public double getTemperature() {
        return this.temperature;
    }
}
