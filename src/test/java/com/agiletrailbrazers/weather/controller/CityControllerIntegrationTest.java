package com.agiletrailbrazers.weather.controller;

import com.agiletrailbrazers.weather.WeatherApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by gustavosousa on 9/16/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CityControllerIntegrationTest {

    @LocalServerPort
    public int port;

    @Test
    public void tempDiff_successfulScenario() throws JSONException {
        String url = "http://localhost:" + this.port + "/tempDiff?lat1=51.51&long1=-0.13&lat2=-22.9&long2=-43.21";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        Assert.assertEquals(200, response.getStatusCodeValue(), 0.0);

        JSONAssert.assertEquals("{\"city1\":{\"id\":1,\"geolocation\":{\"latitude\":51.51,\"longitude\":-0.13},\"name\":\"London\",\"temperature\":49.0},\"city2\":{\"id\":2,\"geolocation\":{\"latitude\":-22.9,\"longitude\":-43.21},\"name\":\"Rio de Janeiro\",\"temperature\":72.0},\"temperatureDifference\":-23.0}",
                response.getBody(), false);
    }

    @Test
    public void tempDiff_cityNotFound() throws IOException {
        String url = "http://localhost:" + this.port + "/tempDiff?lat1=341&long1=-0.13&lat2=-22.9&long2=-43.21";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        TestRestTemplate restTemplate = new TestRestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        Assert.assertEquals(404, response.getStatusCodeValue(), 0.0);

        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = mapper.readValue(response.getBody(), Map.class);
        Assert.assertEquals("City with coordinates (341.0, -0.13) not found.", map.get("message"));

    }
}