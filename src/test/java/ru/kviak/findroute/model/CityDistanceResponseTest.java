package ru.kviak.findroute.model;


import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CityDistanceResponseTest {

    @Test
    public void testGettersAndSetters() {
        CityResponse fromCity = new CityResponse();
        CityResponse toCity = new CityResponse();
        BigDecimal distance = BigDecimal.valueOf(10.5);

        CityDistanceResponse cityDistanceResponse = new CityDistanceResponse();
        cityDistanceResponse.setFromCity(fromCity);
        cityDistanceResponse.setToCity(toCity);
        cityDistanceResponse.setDistance(distance);

        assertEquals(fromCity, cityDistanceResponse.getFromCity());
        assertEquals(toCity, cityDistanceResponse.getToCity());
        assertEquals(distance, cityDistanceResponse.getDistance());
    }

    @Test
    public void testNoArgsConstructor() {
        CityDistanceResponse cityDistanceResponse = new CityDistanceResponse();
        assertNotNull(cityDistanceResponse);
        assertNull(cityDistanceResponse.getFromCity());
        assertNull(cityDistanceResponse.getToCity());
        assertNull(cityDistanceResponse.getDistance());
    }

    @Test
    public void testAllArgsConstructor() {
        CityResponse fromCity = new CityResponse();
        CityResponse toCity = new CityResponse();
        BigDecimal distance = BigDecimal.valueOf(10.5);

        CityDistanceResponse cityDistanceResponse = new CityDistanceResponse(fromCity, toCity, distance);

        assertEquals(fromCity, cityDistanceResponse.getFromCity());
        assertEquals(toCity, cityDistanceResponse.getToCity());
        assertEquals(distance, cityDistanceResponse.getDistance());
    }

}
