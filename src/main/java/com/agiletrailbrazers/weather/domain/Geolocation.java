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

}
