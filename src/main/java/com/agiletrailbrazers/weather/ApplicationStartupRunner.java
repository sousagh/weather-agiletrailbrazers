package com.agiletrailbrazers.weather;

import com.agiletrailbrazers.weather.domain.City;
import com.agiletrailbrazers.weather.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by gustavosousa on 9/16/17.
 */
@Component
public class ApplicationStartupRunner implements CommandLineRunner {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public void run(String... strings) throws Exception {

        this.cityRepository.save(new City("London", 49, 51.51, -0.13));
        this.cityRepository.save(new City("Rio de Janeiro", 72, -22.9, -43.21));
        this.cityRepository.save(new City("Beijin", 55, 39.91, 116.4));

    }
}
