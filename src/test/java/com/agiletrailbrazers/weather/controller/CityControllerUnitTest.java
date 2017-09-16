package com.agiletrailbrazers.weather.controller;

import com.agiletrailbrazers.weather.domain.City;
import com.agiletrailbrazers.weather.domain.report.ReportTemperatureDifference;
import com.agiletrailbrazers.weather.exception.CityNotFoundException;
import com.agiletrailbrazers.weather.service.CityService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * Created by gustavosousa on 9/16/17.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = CityController.class)
public class CityControllerUnitTest {

    @MockBean
    private CityService cityService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getTemperatureDifference_successScenario() throws Exception {

        ReportTemperatureDifference temperatureDifference = new ReportTemperatureDifference(new City("Test1", 1, 1, 1),
                new City("Test2", 2, 2, 2));

        double lat1 = 10;
        double long1 = 2;
        double lat2 = 2;
        double long2 = 9;

        Mockito.when(this.cityService.findDifferenceTemperature(lat1, long1, lat2, long2)).thenReturn(temperatureDifference);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tempDiff")
                .param("lat1", "10")
                .param("long1", "2")
                .param("lat2", "2")
                .param("long2", "9")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(mvcResult.getResponse().getStatus(), 200);

        JSONAssert.assertEquals("{\"city1\":{\"id\":0,\"geolocation\":{\"latitude\":1.0,\"longitude\":1.0},\"name\":\"Test1\",\"temperature\":1.0},\"city2\":{\"id\":0,\"geolocation\":{\"latitude\":2.0,\"longitude\":2.0},\"name\":\"Test2\",\"temperature\":2.0},\"temperatureDifference\":-1.0}",
                mvcResult.getResponse().getContentAsString(), false);
    }

    @Test
    public void getTemperatureDifference_NotFoundScenario() throws Exception {
        double lat1 = 0;
        double long1 = 0;
        double lat2 = 0;
        double long2 = 0;

        Mockito.when(this.cityService.findDifferenceTemperature(lat1, long1, lat2, long2)).thenThrow(CityNotFoundException.class);

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/tempDiff")
                .param("lat1", "0")
                .param("long1", "0")
                .param("lat2", "0")
                .param("long2", "0")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = this.mockMvc.perform(requestBuilder).andReturn();

        Assert.assertEquals(mvcResult.getResponse().getStatus(), 404);
    }
}