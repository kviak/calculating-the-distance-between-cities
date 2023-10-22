package ru.kviak.findroute.model.xml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        String fromCity = "City 1";
        String toCity = "City 2";
        Double distance = 100.0;

        // Act
        Distance distanceObj = new Distance();
        distanceObj.setFromCity(fromCity);
        distanceObj.setToCity(toCity);
        distanceObj.setDistance(distance);

        // Assert
        assertEquals(fromCity, distanceObj.getFromCity());
        assertEquals(toCity, distanceObj.getToCity());
        assertEquals(distance, distanceObj.getDistance());
    }
}
