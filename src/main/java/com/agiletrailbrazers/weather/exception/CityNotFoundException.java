package com.agiletrailbrazers.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by gustavosousa on 9/16/17.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends Exception {

    public CityNotFoundException(String message) {
        super(message);
    }
}
